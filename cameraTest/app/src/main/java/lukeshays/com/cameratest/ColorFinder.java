package lukeshays.com.cameratest;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.graphics.ColorUtils;
import android.util.Pair;

import java.util.Vector;

public class ColorFinder {
    public String area = "";

    private CallbackInterface callback;

    public ColorFinder(CallbackInterface callback) {
        this.callback = callback;
    }

    public void findDominantColor(Bitmap bitmap) {
        new GetDominantColor().execute(bitmap);
    }

    private class GetDominantColor extends AsyncTask<Bitmap, Integer, Integer> {


        @Override
        protected Integer doInBackground(Bitmap... params) {

            Bitmap bitmap = params[0];

            int [] pixels = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels2 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels3 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels4 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels5 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels6 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels7 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels8 = new int[bitmap.getWidth()*bitmap.getHeight()];
            int [] pixels9 = new int[bitmap.getWidth()*bitmap.getHeight()];
            Vector<Pair<int[], String>> myColorVec = new Vector<>();

            bitmap.getPixels(pixels,bitmap.getWidth()*2/3,bitmap.getWidth(),bitmap.getWidth()*2/3,0, bitmap.getWidth()/3, bitmap.getHeight()/3); //TR
            myColorVec.add(Pair.create(pixels, "BR"));
            bitmap.getPixels(pixels2,bitmap.getWidth()*1/3,bitmap.getWidth(),bitmap.getWidth()*1/3,0, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels2,"MR"));
            bitmap.getPixels(pixels3,bitmap.getHeight()*1/3*bitmap.getWidth(),bitmap.getWidth(),bitmap.getWidth()*0/3,bitmap.getHeight()*1/3, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels3,"TM"));
            bitmap.getPixels(pixels4,bitmap.getWidth()*0/3,bitmap.getWidth(),bitmap.getWidth()*0/3,0, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels4,"TR"));
            bitmap.getPixels(pixels5,bitmap.getHeight()*1/3*bitmap.getWidth()+bitmap.getWidth()*1/3,bitmap.getWidth(),bitmap.getWidth()*1/3,bitmap.getHeight()*1/3, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels5,"MM"));
            bitmap.getPixels(pixels6,bitmap.getHeight()*1/3*bitmap.getWidth()+bitmap.getWidth()*2/3,bitmap.getWidth(),bitmap.getWidth()*2/3,bitmap.getHeight()*1/3, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels6,"BM"));
            bitmap.getPixels(pixels7,bitmap.getHeight()*2/3*bitmap.getWidth()+bitmap.getWidth()*0/3,bitmap.getWidth(),bitmap.getWidth()*0/3,bitmap.getHeight()*2/3, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels7,"TL"));
            bitmap.getPixels(pixels8,bitmap.getHeight()*2/3*bitmap.getWidth()+bitmap.getWidth()*1/3,bitmap.getWidth(),bitmap.getWidth()*1/3,bitmap.getHeight()*2/3, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels8,"ML"));
            bitmap.getPixels(pixels9,bitmap.getHeight()*2/3*bitmap.getWidth()+bitmap.getWidth()*2/3,bitmap.getWidth(),bitmap.getWidth()*2/3,bitmap.getHeight()*2/3, bitmap.getWidth()/3, bitmap.getHeight()/3);
            myColorVec.add(Pair.create(pixels9,"BL"));

            int maxSelectedColorPixels = 0;
            for (Pair<int[], String> temp:myColorVec) {
                int selectedColorPixels = 0;
                for (int pixel:temp.first) {



                    if (pixel == 0)
                        continue;

                    double[] labComp = new double[3];
                    double[] labPic = new double[3];

                    if(MainActivity.Red){
                        ColorUtils.colorToLAB(Color.RED, labComp);
                    }
                    else if(MainActivity.Green){
                        ColorUtils.colorToLAB(Color.GREEN, labComp);
                    }
                    else if(MainActivity.Blue){
                        ColorUtils.colorToLAB(Color.BLUE, labComp);
                    }

                    ColorUtils.colorToLAB(pixel, labPic);

                    double distance = ColorUtils.distanceEuclidean(labComp, labPic);

                    if(distance <= MainActivity.colorDistance){
                        selectedColorPixels++;
                    }
                }
                if(selectedColorPixels > maxSelectedColorPixels){
                    area = temp.second;
                    maxSelectedColorPixels = selectedColorPixels;
                }
            }

            return maxSelectedColorPixels;
        }

        @Override
        protected void onPostExecute(Integer dominantColor) {
                if (callback != null) callback.onCompleted(area);
                //callback.onCompleted(dominantColor + (MainActivity.Red ? " red " : MainActivity.Green ? " green " : " blue ") + "pixels in " + area);
        }
    }

    public interface CallbackInterface {
        public void onCompleted(String dominantColor);
    }
}
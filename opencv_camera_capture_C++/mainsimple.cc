#include "opencv2/highgui/highgui.hpp"
#include <iostream>
#include <exception>

using namespace cv;
using namespace std;

int main(int argc, const char** argv)
{
	try
	{
		VideoCapture cap(0); // open the video camera no. 0
	}
	catch (exception& e)
	{
		cerr << e.what();
		return -1;
	}

	if (!cap.isOpened())  // if not success, exit program
	{
		cerr << "Cannot open the video cam" << endl;
		return -1;
	}

	double dWidth = cap.get(CV_CAP_PROP_FRAME_WIDTH); //get the width of frames of the video
	double dHeight = cap.get(CV_CAP_PROP_FRAME_HEIGHT); //get the height of frames of the video

	cout << "Frame size : " << dWidth << " x " << dHeight << endl;

	namedWindow("MyVideo", CV_WINDOW_AUTOSIZE); //create a window called "MyVideo"

	while (1)
	{
		Mat frame;

		bool bSuccess = cap.read(frame); // read a new frame from video

		if (!bSuccess) //if not success, break loop
		{
			cerr << "Cannot read a frame from video stream" << endl;
			break;
		}
		
		// do stuff with frame

		imshow("MyVideo", frame); //show the frame in "MyVideo" window

		if (waitKey(30) == 27) //wait for 'esc' key press for 30ms. If 'esc' key is pressed, break loop
		{
			cout << "esc key is pressed by user" << endl;
			break;
		}
	}
	return 0;
}
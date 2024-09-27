Question 1: Imagine you are new to the programming world and not proficient enough in coding. But, you have a brilliant idea where you want to develop a context-sensing application like Project 1.  You come across the Heath-Dev paper and want it to build your application. Specify what Specifications you should provide to the Health-Dev framework to develop the code ideally.

Answer :  The health-dev framework is using multiple sensors to capture the human data. The inputs to be provided can be divided into 3 elements:

Front-end : 
Sensor specification: The user can specific which single to be sensed depending on the activity the person is doing. So at the front-end there should be an option to turn different sensors on or off by the user.
Sensor subcomponents: The user should be given an option to select from multiple communication protocols. 
Sensor connections: I would like to add the facility of the sensors running the code in offline mode as well if the data is sensed already and provide a result in case it detects anything unusual.

Algorithm:

Step 1 : The user opens the app
Step 2: The user selects and turns on different sensors which he/she wants to measure the details of.
Step 3: The user will click on the start button to turn on the sensing and once sufficient data is collected to analyze the information, the sensor automatically turns off.
Step 4: Now the data collected can be from multiple sensors and will be stored in a database for training purpose as well.
Step 5: The code to calculate output from various sensors will be provided to the system.
Step 6: The system will search for any available code to provide a result based on the sensor selected.
Step 7 : If matching code-found the smart phone repository it will return result on the screen and will alert the user in case of any abnormality.
Step 8: if no matching code is found, a call will be made to the BSNBench suite to get more information on the data to be calculated.
Step 9: The final result will be calculated and provided to the user.

Hardware : 
Smart phone with multiple sensors to sense elements like : Temperature, humidity, pulse, heart rate, breathing speed - electrocardiogram, electroencephalogram, and
accelerometer sensors;
OS : OS strong and smart enough to run these codes and store the required information and high RAM required to run the functions in the backend.
Network : A high speed connection would be required to connect to various databases and sensors and transfer the data.

Requirement    Minimum                             Recommended
OS             Android/iOS latest version          Android/iOS latest version
RAM            8 GB                                16GB
Disk Space     8 GB                                Solid state drive with 16 GB or more   
Screen         1920 x 1080                         1920 x 1080
Resolution  

  
Question 2: In Project 1 you have stored the user’s symptoms data in the local server. Using the bHealthy application suite how can you provide feedback to the user and develop a novel application to improve context sensing and use that to generate the model of the user?
Answer 2: As mentioned in the research paper the bHealthy application uses two types of applications : a) assessment and b) training. The assessment applications use feedback from activity, heart signals, and brainwaves to detect mental state of a user, such as frustration, relaxation, boredom, and excitement, and suggest activities. 
The training applications continuously monitor the activity performance and physiological signals of the user to compute the effectiveness of the wellness application.
In our case for project 1 we have monitored the heart rate and the respiratory rate of the user. Since we are using the flash from the camera and the pulse rate to measure the rates, then making a function call and calculating different rates and then storing it in the database.  
To improve our current context sensing application, we can also create a training database as suggested in the research paper. The database can collect values from different people and different sensors to compare and provide a result about the health of the user based on the training data and the calculation result received. We can also provide an alert to the user in case of any abnormality in the result.
If we have stored the data collected from the user in local server, we’ll have to import the test cases or the training data in our app to provide a better response to the user.

Question 3: A common assumption is mobile computing is mostly about app development. After completing Project 1 and reading both papers, have your views changed? If yes, what do you think mobile computing is about and why? If no, please explain why you still think mobile computing is mostly about app development, providing examples to support your viewpoint.
Answer 3: After completing Project 1 and reviewing both papers, my views on mobile computing have evolved. While I still think that mobile computing involves app development but now I also understand that knowledge of different things it requires to create that single app:
1. UX/UI
The UX/UI has become one of the most important factor in developing any app today. The interface with which a user interacts needs to be easy to use, but still informative and personalized. For instance, context-aware applications, such as those providing real-time traffic updates or personalized notifications based on location, demonstrate that mobile computing is about creating experiences that are responsive to the user’s environment and needs.
2. Hardware and Sensors
During implementation of Project 1, I got to know about multiple sensors that are embedded in the phone, the research paper helped me understand the usage of various elements and sensors and the importance of hardware to be used. These days phones have become really smart and can provide us with different functionalities, this assessment helped to get more insight on the hardware and sensor requirement and usage.
3. Connectivity and Networking

While doing the assessment we did not store anything remotely, however it helped me understand the concept of connectivity and local servers. The assignment also helped me understand the topic permissions in the phone and the implementation of it. The research papers helped me understand the concept of connectivity since it mentioned the usage of options like WiFi, bluetooth and sensing data in real-time
Conclusion

In summary, I think that while app development is a crucial component of mobile computing, it is not the sole focus. Mobile computing involves a wide range of factors including hardware integration, connectivity and user experience. These elements collectively define the field and demonstrate that mobile computing is a multifaceted domain that encompasses more than just creating applications. 

# About FlexFit

This is collaborative project done as part of an academic course called "Mobile Development". The *front-end* is written in **Java** and the *back-end* in **PHP**.

>The PSF (Panhellenic Association of Physiotherapists) in the context of digital transformation Greece 2.0, decided to conduct a tender for the assignment of a project to a company that will develop a physical therapy management system to make it available for free to all its members. The mobile application that will be created will have three users, with the following functions:

>***PSF user:*** \
**R1:** Creation of a Physiotherapy Clinic (Name, Address, VAT number) \
**R2:** Create New Provision (Code, Name, Description, Cost per Session)—eg.
"MX001", "Sports Massage", "Type of therapeutic massage aimed at athletes. The species
of sports massage that have been established are four: (a) maintenance, (b) preparation, (c)
rehabilitation, and (d) medium-competitive. The techniques used in sports massage
are particularly effective in the management of acute and chronic injuries, in reducing
muscle spasm intensity and pain sensation in injured-overloads
muscles, as well as in the healing and creation of new, strong and elastic tissues.", "45 euros"

>***Doctor User:*** \
**R3:** Create Patient (Name, Address, SSN) \
**R4:** View Patient History \
**R5:** Search and Select Patient \
**R6:** Show Weekly Date Plan \
**R7:** View and Manage Appointment Requests \
**R8:** Add Visit and Activity Log 

>***Patient User:*** \
**R9:** Request an Appointment \
**R10:** Analytical Statement of Financial Movements

## Installation for the back-end
The back-end of this application has been deployed in a web server, so the app will run automatically with the default configuration. But if you want to deploy the back-end (Database and PHP) you can deploy the whole back-end using Docker. For this option you can check the [Apache-PHP-MariaDB-phpMyAdmin-setup-using-Docker](https://github.com/pnasis/Apache-PHP-MariaDB-phpMyAdmin-setup-using-Docker) repository for further instructions. Then you have to change the IP from the *MainActivity* class and the *network_security_config.xml* located in the xml folder.

## Installation for the front-end
1. You need to install [Android Studio](https://developer.android.com/studio/install) in order to run the Java front-end.
2. You need to clone this repository
3. When you open the project in Android Studio, go to *local.properties* file and change the value of *sdk.dir=* to the path that you have previously install your SDKs.
4. Finally, you can hit **Build** and **Run** and you are ready to go.

## Contributing

>Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

## License

This project is under the [Apache 2.0](https://choosealicense.com/licenses/apache-2.0/) licence.

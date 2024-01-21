# Mobile 3D Modeling Application of the Human Body

## Introduction

This study introduces an innovative mobile application dedicated to 3D modeling of the human body, complemented by a web-based administration interface. The mobile application delivers an immersive experience, enabling users to explore detailed three-dimensional models of the human body. Simultaneously, the web interface provides administrators with a user-friendly means of managing objects and associated data. By combining the power of mobile 3D modeling with efficient resource management through a centralized web interface, our solution aims to offer a comprehensive experience for both users and administrators. 

## I. Description

Our 3D modelling application for the human body combines technological inno-
vation with anatomical education, offering a comprehensive platform for users
and administrators alike.

## II. Architecture
The software adopts a versatile and scalable architecture, utilizing a client-server
model with distinct components for the mobile application and web administra-
tion interface. The mobile app, developed using Flutter and Dart, provides an
immersive 3D exploration experience. Simultaneously, the web-based adminis-
tration interface, built with Angular, ensures efficient management of objects and
associated data. These components interact seamlessly, supported by a backend
implemented in Spring Boot. The entire system is containerized using Docker
and orchestrated with Kubernetes for enhanced deployment flexibility.

![Architecture](/images/archi.png)

## III. Functionalities

The application offers a myriad of functionalities to enrich the understanding of
human anatomy. Users can interact with detailed 3D models, navigating through
various layers to focus on specific organs or systems. Educational content, by
allowing the user to add notes to a specific organs , enhances the learning expe-
rience. Administrators, facilitated by the web interface, can efficiently manage
objects and data, ensuring a streamlined workflow. The integration of AWS
services ensures reliable cloud support, and GitLab-CI automates continuous
integration and deployment processes.

## IV. Mobile Application Examples

In the illustrative example discussed in this section, our 3D modeling application
offers a comprehensive exploration of the cardiovascular system for medical stu-
dents. Upon launching the mobile application, the student navigates through a
user-friendly interface, first encountering a list of categories, as shown in Figure
2(a). Subsequently, the student selects a specific module category, revealing a
detailed list of objects within that category, as illustrated in Figure 2(b). The ap-
plication then presents a highly detailed 3D model of the chosen object, as seen
in Figure 3(a), allowing the student to employ intuitive gestures like zooming
and rotating for in-depth exploration.
To enhance the learning experience, the student has the capability to peel away
layers of the 3D model, providing a closer examination of internal structures and
facilitating a deeper understanding of anatomical relationships. Additionally, the
student can actively engage with the content by adding personal notes directly
onto the 3D object, as demonstrated in Figure 3(b).(a) List of categories (b) List of objects in category
<p align="center">
<img src="/images/1.jpg" alt="app demo" width="250"/> <img src="/images/2.jpg" alt="app demo" width="250"/> 
</p>
<p align="center">
<img src="/images/3.jpg" alt="app demo" width="250"/> <img src="/images/4.jpg" alt="app demo" width="250"/> 
</p>

## V. Web Application Examples
Home page:
![home page](/images/dash.png)

Management of Categories:
![Management of Categories](/images/cat.png)

Management of 3d Organs:
![Management of 3d Organs](/images/objs.png)

View 3D Organs:
![View 3D Organs](/images/demo.png)

Edit 3D Organs:
![Edit 3D Organs](/images/edit.png)

## VI. CI/CD Pipeline
🚀 Prod Environment 🚀 :
In contrast, the prod environment was hosted on AWS as well, but we selected Kubernetes for container orchestration. This setup included one master node and two worker nodes, ensuring high availability and scalability.

🛠️Pipeline Overview🛠️ :
An essential component of this project was the establishment of a CI/CD (Continuous Integration/Continuous Delivery) pipeline. This pipeline significantly optimized our development workflow, minimizing manual errors and expediting our release cycle.
Key highlights of the CI/CD pipeline:
◾ Version control integration for efficient collaboration.
◾ Automated code integration and testing.
◾ Building Docker images for each build to ensure consistency across environments.
◾ Seamless deployment to prod environment.
◾ Rollback capabilities in case of unforeseen issues.

![ci/cd pipeline](/images/pipeline-archi.png)


## VII. Running the Docker Environment

1. Start Docker and execute the following command in the root of the project to launch the containers:

    ```bash
    docker-compose up -d
    ```

2. The Docker Compose file (`docker-compose.yaml`) orchestrates four containers:

   - **Database Service (PostgreSQL)**
   - **pgAdmin Service** at [http://localhost:5050](http://localhost:5050) for database management.
   - **Backend Service** at [http://localhost:8080](http://localhost:8080).
   - **Frontend Service** at [http://localhost:9090](http://localhost:9090).


3. To access the frontend, use the following credentials for the Admin account:

- **Email:** admin@admin
- **Password:** admin@admin


## VIII. Generate the .apk file for the mobile application

1. To generate the .apk for the mobile application, navigate to `/mobile/lib/network/api/Endpoints.dart` and update the URL for the backend.

2. Run the following commands to generate the .apk:
    ```bash
    flutter clean 
    flutter build apk --debug
    ```

3. Once the build process is complete, locate your .apk file in `/mobile/build/app/outputs/apk/debug`.



# Prototype Web Link
- [FDAlerts Home]
or
[FDAlerts IP Link]

# Approach for the 18f Challenge
There are numerous Agile-aligned frameworks: Scrum, Kanban, Feature-Driven Development etc. We examined the parameters of this challenge-1 week delivery, needing to demonstrate process in action, ability to discover and adapt on an hourly / daily basis and the tenets of the Digital Services Playbook. While Scrum is the most popular framework, especially when used with XP, we believe that Scrum's ceremonies and focus on heads down, work the committed/forecasted plan for the iteration were less applicable to the 18f challenge. Kanban boards are effective at helping teams become more productive while reducing the amount of workload stress many project managers, developers and team members feel during a project lifecycle.Therefore, we determined that Kanban in combination with XP practices were the most appropriate frameworks to get the project needs delivered because we could: 
 - Visually see work in progress (WIP)
 - Instantly understand delays and impediments and take steps to resolve them  
 - Improve communication amongst all team members
 - Empower teams to self-manage visual processes and work flows 
 - Inspire team collaboration
# Assembling the Team
We assembled a team of ten team members consisting of the following roles:
Roles
- Product Manager
- Agile Coach
- Technical Architect
- Business Analyst
- Interaction Designer / User Researcher / Usability Tester
- Frontend Web Developer 
- Delivery Manager -
- Writer / Content Designer /Content Strategist
- Backend Web Developer
- DevOps Engineer

The team self-selected a Product Manager as the team member to lead this effort and responsible for the outcomes.
# Preparation
To bootstrap the Kanban execution, we needed to get work items (or cards) loaded into the beginning of the pipeline in a priority order focused on maximizing the value of the solution being created. To this end, we performed two preparatory steps:
1.	Rapid ideation and idea selection
2.	Backlog grooming towards a targeted Minimal Viable Product (MVP)

For our rapid ideation process, we reviewed the parameters of the challenge with all team members. We then asked everyone to independently research the OpenFDA capabilities and data sets and come up with one or more ideas to bring to the next part of the process. Once everyone had a good basis of understanding of the problem set, we assembled the team again into an ideation exchange meeting. During this meeting, we created cards of all the ideas from each team member, brainstormed new ideas, and the performed a rapid prioritization to reach a consensus on the idea with the strongest alignment to the 18f challenge's goals. The selected idea was an alerting mechanism for adverse events and enforcement reporting on Food, Drug and Device areas.
We then took a small sub-team to create an initial product backlog. The initial draft of the product backlog had 30 user stories. The team also used Ideation, empathy maps and insight statements as the 3 human-centered design techniques or tools here to produce UI mockups and a UI style guide.  A design prototype using iRise was created and tested for usability through users on the team. That backlog and UI mockups were then brought to the full team the next day to determine a Minimal Viable Product (MVP) version of a software system that would realize the target idea. This MVP backlog was used to bootstrap the Kanban board upon which product build did commence.
Additionally, we settled on a target architecture which included: 
- Spring Core
- AngularJS
- Java
- Tomcat
- MySQL
- Spring MVC - API for RESTful Webservices

# Execution
Our Kanban board consisted of the following 5 columns each representing a different work process in the delivery flow:
- Backlog
- Selected for Development
- In Progress
- Done
- Release

While the team was constantly collaborating during the solution delivery execution, the team met on a daily basis for a stand-up to ensure full team alignment and an opportunity for re-synchronization of the coming day's goals in order to best ensure delivery of the target MVP system. We modeled the daily stand-up around Scrum's practice of the three questions.
After the first couple days, the UI was testable, so every day thereafter we  performed some usability testing.
- [FDAlerts.com] is deployed on Amazon Web Services (AWS) which is a widely known Infrastructure as a Service tool. We chose a t2.small Elastic Computer instance to run our application. We used one of our custom AMI’s to spawn the instance with Chef-client preinstalled to better streamline our DevOps process.
- We included the XP practice of Continuous Integration in conjunction with our Kanban delivery execution that required developers to integrate code into a shared repository several times a day. Each check-in was then verified by an automated build, allowing teams to detect problems early.
- We used Jenkins, an open-source software application to monitor, build, and assist in deployment of FDAlerts.com. Our Jenkins project is linked to our Github repository build to the master branch. Builds are triggered when changes are pushed to GitHub. When triggered, Maven finds the root pom.xml file and runs the goals set by Jenkins. If the build succeeds code is deployed to our EC2 instance through Amazon’s Code Deploy plugin, and SonarQube generates an analysis. 
- We used docker on our Amazon EC2 instance to take advantage of the minimal runtime requirements of the application allowing for rapid application deployment. Creating docker images for our applications also gives extended portability. If the build fails emails are sent to the appropriate members of the team. 
- Hardware monitoring is done through Amazon’s CloudWatch. 
- We are also using SonarQube for code coverage and vulnerability analysis. In order to increase scalability efficiency, stability, and reduce costs we chose Chef as our configuration management tool. Our instance was configured by our Chef server as a client node with a role that allowed all the required dependencies to be installed and running on our EC2 instance. Build failures were produced due to compilation issues, code check issues, or unit test failures. Immediate notification to the developer and the team is sent on any of these detected issues.
# Team Responsibilities
The team created a few norms around code responsibilities such as 
- Check in frequently and iteratively
- Don’t check in broken code
- Don’t check in untested code
- Don’t check in when the build is broken

# Conclusion
We completed the timebox associated with the 18f challenge with all MVP targeted user stories completed plus a few additional user stories. The Product Owner facilitated a review session, with participation of the rest of the delivery team and a group of associates from our companies outside of the delivery team. The outside group provided an independent set of eyes to review the working prototype. Additionally, this group provided feedback
to ensure that the overall product delivery met with the goals of the project, including the realization of the target idea and meeting all of the evaluation criteria outlined in the RFQ.

# Licenses
- Twitter Bootstrap (MIT License - https://en.wikipedia.org/wiki/MIT_License)
- AngularJS (MIT License - https://en.wikipedia.org/wiki/MIT_License)
- Java - (http://www.oracle.com/technetwork/java/javase/overview/licensees-jsp-136136.html)
- Apache Tomcat (Apache License 2.0 - https://www.apache.org/licenses/)
- MySQL (GPL (version 2) or proprietary - https://gnu.org/licenses/gpl.html)
- Spring (MVC) Framework - API for RESTful Webservices (Apache License 2.0 - https://www.apache.org/licenses/)
- Jenkins (https://jenkins-ci.org/mit-license)
- Chef (https://www.chef.io/online-master-agreement/) 
- Sonarqube (http://www.sonarqube.org/downloads/license/)

# Website
- [FDAlerts Home]
- [Git Repo]
- [Docker Hub]

# Evidence
U.S. Digital Services Playbook principles were used extensively in our approach and the evidences to the approach are documented in here
- [Evidence for Section E]

[FDAlerts Home]:(http://fdalerts.com)
[Git Repo]:https://github.com/RigilCorp/RIGIL-18F-Pool2
[Evidence for Section E]:https://github.com/RigilCorp/RIGIL-18F/tree/master/documents/evidence
[FDAlerts IP Link]:http://52.5.29.80:8080/rigil-18f
[Docker Hub]:https://registry.hub.docker.com/u/agiletrailblazers/fdalerts/

# Staff Cover
Check if understaffed

# Allan Gamal

  <h2>Staff Cover Application</h2>
  <p>Sharing some of the process of the making of this project</p>

  <h2>Project</h3>
    <p>Making an GUI application to check given department is understaffed by first checking the competency of the employees and then try every combination of the workload. Customized to be suited for a factory setting. 
</p>

   <h2>Thoughts and goals going into the project</h3>
     <ul>
        <li>Make a GUI using java and JavaFX</li>
        <li>Fresh on object oriented programming, will come handy</li>
        <li>Design the GUI so new features is realtive easy to implements in the future</li>

</ul>

  <h2>Recognizing some potential issues</h2>
    <ul>
        <li>Learning JavaFX, might be time consuming</li>
   </ul>
   
   ----
   **Scroll down for a diary like documentation**
   --
   <h3>Design of Classes</h3>
   
   <p>Designing of the classes needed for the project, will probably change things later</p>

  <img src="/images/classDesign.png" width="60%" height="60%" >
  
  --------
  
  <h3>GUI Design</h3>
   
  <p>Designed the basic template for the application with a home, teams and employee tabs, giving it room to expande the project with potentially more tabs. Also added added, change and remove-buttons for both the teams and employees tab to their respective table.
  
  Next thing to be added is the button functionality to add team and fitters so a basic version of the application can be tested. 
  </p>

  <img src="/images/teams.jpg" >
  <img src="/images/personal.jpg" >
  
  --------
   
   <h3>Design update and functionality implementations</h3>
   
  <p>
  Updated the design on the "Team" page so I have list of stations and employees in two different list within the team, instead of making a table. Table in this case do not work since I wanted two independently columns. Also added functionality to add team/remove, stations and employees, and also able to add/remove competency to a list within every employee.
  </p>
  
<h4>Design update and added functionality of the team page</h4>
  <img src="/images/teams2.jpg" >
  
  <h4>Add/remove employee functionality</h4>
  <img src="/images/personal2.jpg" >
  
  <h4>Add/remove competency of employee functionality</h4>
  <img src="/images/addcomp.jpg"  width="50%" height="50%" >
  
  --------
   
      
   <h3>I asked for the application to tackle an NP-problem (application failed, I am an idiot)</h3>
   
  <p>
  I am pretty much done with the application. I made so when pressing a button, it will give you up to 10 possible combinations of "line-up" of fitters to stations. If there is there is missing two fitters to be able to cover every station, it will introduce fitters "TOM-1" and "TOM-2" and try every combination with with them lik the picture below. It will work with up to 6 introductions of "TOM-x". So it will be possible for the application to cover every station if up to 6 stations cannot be covered. 
  </p>
  
<h4>Possible combinations of stations covored with full cover</h4> 
<img src="/images/calc1.png" >

<h4>Possible combinations of stations covored with 2 fitters missing</h4> 
<img src="/images/calc2.png" >
  
<h4>The application is garbage with any larger sets</h4>
<img src="/images/np.png" >
 <p>
   I've tried my algorithmen to calculate every possible combinations of fitters covering stations, and it worked flawlessly. But I only tried it with relative small data sets (the idea was to have a data set at leat 5x). When increasing the data set with about 30%, the calculation will slow down significally. Consider this: You want to calculate the shortest path between a number of destinations, and you will have to calculate every possible outcome first. However, for every new destination you introduce, you will increase the complexity of the system, compounding with every new element, making the calculation exponentially harder and longer to solve. This makes the problem NP-complete, meaning that such problems are not believed to be efficiently solvable for large sets of data.
  </p>

 <p>
   Hopfully the value I gainged of this failed application is that I (hopfully) can identify an NP-problem before trying to solve it. 
  </p>
 


Jackson Eshbaugh
CS 150 (Data Structures & Algorithms)
Project 1: River Ecosystem Simulator

TODO:
- Run all unit tests
- General code review
- SUBMIT!

Problems & Solutions:

- I created a static method to generate random genders, but the bound for the Random#nextInt() method I set was 1, not 2. This became 
  extremely obvious when I ran unit tests and took some statistics (mean age, % male, and % female, specifically). Not one female was randomly
  generated, so I knew something was up with my method. That's when I spotted my mistake and updated the #nextInt() bound to 2, fixing the issue.
  
- I found a bug with the array updating logic in the River class, where some Animals were updated twice. I realized that if an animal were to move to the
  right, it would actually be updated again. So, I created an ArrayList<Animal> which I added every animal I updated to it. That way, I can be sure that
  animals are only updated once by simply using #contains(animal). I clear the ArrayList after each cycle so it can be started new again. I also simply
  add the newborn animals to this ArrayList too, which allows me to pass over their moves as well (because a newborn begins to move the cycle after it is
  born).
  
Experience:

- I used some of my (super basic) statistics skills to take the standard deviation of the ages and interpret it. I did some calculations to ensure that the mean
  falls on a 95% confidence interval. This would be the first time I've used a programming language to do statistics (besides in MATH 186 using R). Trivial as it
  is, I always like to note where I get the oppertunity to connect different subjects together.

- I got to practice properly testing things for the first time. I have bad habits of just coding and fixing as I go, but setting up specific tests
  to see what my code is doing is valuable. I didn't learn how to test when I learned Java, which is unfortunate, but I'm trying to rectify that now.
  I taught myself how to use unit tests in BlueJ, and those have been extremely helpful in validating each piece of my code.
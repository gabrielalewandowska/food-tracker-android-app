# Food Tracker

My second individual project at CodeClan.

## Project Brief

Goal: Practice OO and UI design with what we learned in Java / Android weeks and research how to persist data.

## MVP

You are required to write an Android app that allows a user to track the food they eat. Users should be able to enter what they eat and when (date/time) and for what meal.

## My Project

I have created an SQLite database which stores different food products (e.g. oats, bananas etc.) and their nutritional value per 100g as well as food history (all food items eaten on a certain date). Using the navigation drawer the user can go to the Diary view which automatically opens on the current date. The date can be changed using a calendar (CalendarFragment which extends DialogFragment). Then the user can select a food item from a spinner which lists all food items currently in the database. Quantity can be entered using a number input field below. When the user presses the "save" button, their new food history is saved to the database and the app displays a toast saying "Entry saved!".


---------

Since I had only six days to finish the project I was only able to meet the MVP. However, during that time I managed to learn how to make navigation drawers as well as how to work with SQLite databases and Fragments. I intend to continue working on the project in future in order to add the following functionality:
- History Fragment where the user could select month and year from spinners and the app would show a summary of what they ate in the selected period (e.g. March 2017 => oats: 10kg, apples: 2kg etc.).
- Add New Food Fragment where the user would be able to add their own foods to the database and specify kcal and macronutrients/100g.
- Eventually I would like to expand the database so that it would include vitamins and minerals per 100g of a given product so that the app could give the user feedback on the nutritional targets they set in a given day or on average in a given month.

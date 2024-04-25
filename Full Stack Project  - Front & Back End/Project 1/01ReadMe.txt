- Note:  m4 = 3 for my table as middle digit of my student number is 7.


- Submitted Files:
	1.	 page1.html
	2.	 page2.html
	3.	 Page2.css


- Role of Each File:

  1. page1.html: This file represents the structure and content of the first page of the website. It contains an HTML form with two input fields for first name and last name. It also includes JavaScript code for handling form submission and displaying an alert with the hidden field values.

  2. page2.html: This file represents the structure and content of the second page of the website. It includes various HTML elements such as tables, buttons, radio buttons, checkboxes, images, and a select dropdown. It also includes JavaScript code for handling events like button clicks, value changes, and displaying alerts with selected options or changed values.

  3. Page2.css: This file contains custom CSS styles for the elements present in page2.html. It defines styles for the body, table, cells, buttons, select dropdown, and text input.


- Customization for Each Page and Element:
 1. Page1.html:
    a. Added custom CSS styles to the "Submit" button.
    b. Included JavaScript code for handling form submission and displaying the hidden field values in an alert.

 2. Page2.html:
    a. Linked the Page2.css file for applying custom styles.
    b. Added custom CSS styles for various elements like buttons, table cells, select dropdown, and text input.
    c. Included JavaScript functions for handling events like button clicks, value changes, and displaying alerts with selected options or changed values.
    d. Buttons:
   	- Custom CSS styles:
       - Background color: #4caf50 (normal state), #45a049 (hover state)
       - Text color: white
       - Padding: 5px 10px
       - Font size: 14px
       - JavaScript code:
       - Added an onclick event handler to the "Button1" button to trigger the "simulateSubmitForm()" function.

	Radio Buttons: Added onchange event handlers to all radio buttons to trigger the "showChangedValue(event)" function when the value changes.

	Paragraph: Font size: 60%

	Images:
       - Specified width: 200px
       - Specified height: 100px

	Select Dropdown: Set width to 100%

	Table:
     	- Applied border-collapse: collapse to have a consistent border style between table cells.
     	- Set width to 100%

	Text Input:
       - Set width to 100%
       - Added an onchange event handler to the text input field to trigger the "alerttextChanged()" function when the value changes.


- Role of JavaScript Code: The JavaScript code in both page1.html and page2.html is responsible for adding interactivity and functionality to the web pages. It performs the following tasks:
  1. In Page1.html: The "submitForm()" function retrieves the values of the hidden input fields and displays them in an alert when the "Submit" button is clicked.

  2. In Page2.html: 
	a. The "showSelectedOption(select)" function displays the selected option from a dropdown in an alert when the value changes.
	b. The "showChangedValue(event)" function displays the changed value of a radio button in an alert when it is selected.
	c. The "simulateSubmitForm()" function retrieves the value of a hidden field and displays it in an alert when the corresponding button is clicked.
	d. The "alerttextChanged()" function retrieves the value of a text input field and displays it in an alert when the value changes.
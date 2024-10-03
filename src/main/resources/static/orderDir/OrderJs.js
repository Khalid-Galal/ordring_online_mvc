//==================Data collected at start of page===============================
//Table under placing order that collect from user
// Initialize an array to store the orders for the main table
let orders = JSON.parse(localStorage.getItem('orders')) || [];
let isEditing = false;
let editIndex = -1;

//Table right placing order that collect from DataBase
// Fetch orders for the right-side list from the API when the page loads
document.addEventListener('DOMContentLoaded', function() {
    fetchOrdersForSideList(); // Fetch and display orders for the right-side list
    displayOrders(); // Display local orders on the main table when the page loads
});

// Function to fetch orders from the API for the right-side list
function fetchOrdersForSideList() {
    fetch('/orders') // Replace '/orders' with your actual API endpoint
        .then(response => response.json())
        .then(data => {
            displayOrderList(data); // Pass the fetched data to displayOrderList
        })
        .catch(error => console.error('Error fetching orders:', error));
}

//==================Data collected at start of page===============================

//==================Logic for right table===============================

// Function to display the orders in the right-side list (right order list)
function displayOrderList(apiOrders) {
    const orderListTable = document.getElementById("orderListTable");
    orderListTable.innerHTML = ""; // Clear previous entries

    // Loop through the fetched orders from the API and display them in the right-side list
    apiOrders.forEach(order => {
        const row = orderListTable.insertRow();
        row.insertCell(0).innerText = order.orderName; // Display order name in the first column
        row.insertCell(1).innerText = order.quantity;  // Display quantity in the second column
    });
}
// Function to handle the "Complete Order" button click
function completeOrder() {
    if (orders.length === 0) {
        alert('No orders to complete.');
        return;
    }

    fetch('/orders/bulk', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orders)
    })
        .then(response => {
            if (response.ok) {
                alert('Orders successfully saved to the database.');
                //orders = []; // Clear the local orders array after successful saving
                //saveOrdersToLocalStorage(); // Clear localStorage
                displayOrders(); // Clear the main table
                fetchOrdersForSideList(); // Refresh the right-side order list from the backend
            } else {
                alert('Failed to save orders.');
            }
        })
        .catch(error => {
            console.error('Error saving orders:', error);
            alert('An error occurred while saving the orders.');
        });
}

//==================Logic for right table===============================

//==================Logic for under table===============================

// Save orders to localStorage
function saveOrdersToLocalStorage() {
    localStorage.setItem('orders', JSON.stringify(orders)); // Convert array to JSON string and store it
}
// Function to place or update an order in the main table
document.getElementById('orderForm').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get the values from the form
    const foodItem = document.getElementById("foodItem").value;
    const quantity = document.getElementById("quantity").value;

    // Check if we're editing an existing order
    if (isEditing) {
        orders[editIndex] = {
            orderName: foodItem,
            quantity: quantity
        };
        isEditing = false;
        editIndex = -1;
        document.getElementById("submitButton").innerText = "Place Order"; // Reset button text
    } else {
        const order = {
            orderName: foodItem,
            quantity: quantity,
            price:0
        };
        orders.push(order);
    }

    // Save the orders to localStorage
    saveOrdersToLocalStorage();

    // Display the updated orders in the table
    displayOrders();

    // Reset the form
    document.getElementById('orderForm').reset();
});
// Function to display orders in the main table
function displayOrders() {
    const orderTable = document.getElementById("orderTable");
    orderTable.innerHTML = ""; // Clear table before inserting rows

    // Dynamically insert rows into the main table
    orders.forEach((order, index) => {
        const row = orderTable.insertRow();
        row.insertCell(0).innerText = order.orderName;
        row.insertCell(1).innerText = order.quantity;
        row.insertCell(2).innerHTML = `
        <div class="action-buttons">
            <button class="btn btn-warning btn-sm btn-icon" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit Order" onclick="editOrder(${index})">
                <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-danger btn-sm btn-icon" data-bs-toggle="tooltip" data-bs-placement="top"  title="Delete Order" onclick="deleteOrder(${index})">
                <i class="fas fa-trash-alt"></i>
            </button>
        </div>`;
    });

    // Re-initialize tooltips after adding new rows
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
}
// Function to edit an order in the main table
function editOrder(index) {
    const order = orders[index];
    document.getElementById("foodItem").value = order.orderName;
    document.getElementById("quantity").value = order.quantity;
    order.price=0;
    order.

    isEditing = true;
    editIndex = index;

    document.getElementById("submitButton").innerText = "Update Order";
}
// Function to delete an order from the main table
function deleteOrder(index) {
    orders.splice(index, 1); // Remove the selected order by index
    saveOrdersToLocalStorage(); // Update localStorage after deletion
    displayOrders(); // Refresh the order table
}

//==================Logic for under table===============================
// Storing token in localStorage after login
localStorage.setItem("authToken", token);

// Sending token in the Authorization header in future requests
fetch('/order', {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem("authToken")
    }
});

// Initialize an array to store the orders
let orders = [];
let isEditing = false; // Track if we're editing an order
let editIndex = -1;    // Track which order is being edited

// Load orders from localStorage if they exist
if (localStorage.getItem('orders')) {
    orders = JSON.parse(localStorage.getItem('orders'));
    displayOrders();
    displayOrderList(); // Display the list on the right side
}

// Function to place or update an order
document.getElementById('orderForm').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get the values from the form
    const foodItem = document.getElementById("foodItem").value;
    const quantity = document.getElementById("quantity").value;

    // Check if we're editing an existing order
    if (isEditing) {
        // Update the existing order
        orders[editIndex] = {
            foodItem: foodItem,
            quantity: quantity
        };
        isEditing = false; // Reset the editing flag
        editIndex = -1;    // Reset the edit index
        document.getElementById("submitButton").innerText = "Place Order"; // Change button text back to "Place Order"
    } else {
        // Create a new order
        const order = {
            foodItem: foodItem,
            quantity: quantity
        };

        // Add the order to the orders array
        orders.push(order);
    }

    // Save the updated orders array to localStorage
    saveOrders();

    // Display the updated orders in the table
    displayOrders();
    displayOrderList(); // Update the list on the right

    // Reset the form
    document.getElementById('orderForm').reset();
});

// Function to display orders in the table
function displayOrders() {
    const orderTable = document.getElementById("orderTable");
    orderTable.innerHTML = "";

    // Dynamically insert rows into the table
    orders.forEach((order, index) => {
        const row = orderTable.insertRow();
        row.insertCell(0).innerText = order.foodItem;
        row.insertCell(1).innerText = order.quantity;
        row.insertCell(2).innerHTML = `
        <div class="action-buttons">
            <button class="btn btn-warning btn-sm btn-icon" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit Order" onclick="editOrder(${index})">
                <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-danger btn-sm btn-icon" data-bs-toggle="tooltip" data-bs-placement="top" title="Delete Order" onclick="deleteOrder(${index})">
                <i class="fas fa-trash-alt"></i>
            </button>
        </div>`;
    });

    // Re-initialize tooltips after adding new rows
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
}

// Function to display the orders in the right-side list
function displayOrderList() {
    const orderListTable = document.getElementById("orderListTable");
    orderListTable.innerHTML = "";

    orders.forEach(order => {
        const row = orderListTable.insertRow();
        row.insertCell(0).innerText = order.foodItem;
        row.insertCell(1).innerText = order.quantity;
    });
}

// Function to save the orders array to localStorage
function saveOrders() {
    localStorage.setItem('orders', JSON.stringify(orders));
}

// Function to delete an order
function deleteOrder(index) {
    orders.splice(index, 1);  // Remove the selected order by index
    saveOrders();  // Update localStorage
    displayOrders();  // Refresh the order table
    displayOrderList();  // Update the list on the right
}

// Function to edit an order
function editOrder(index) {
    // Load the selected order's values into the form for editing
    const order = orders[index];
    document.getElementById("foodItem").value = order.foodItem;
    document.getElementById("quantity").value = order.quantity;

    // Set the edit mode
    isEditing = true;
    editIndex = index;

    // Update the "Place Order" button text to "Update Order"
    document.getElementById("submitButton").innerText = "Update Order";
}

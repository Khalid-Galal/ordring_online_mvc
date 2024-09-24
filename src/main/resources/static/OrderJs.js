document.getElementById('orderForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const foodItem = document.getElementById('foodItem').value;
    const quantity = document.getElementById('quantity').value;

    // Simple front-end validation
    if (!foodItem || quantity <= 0) {
        document.getElementById('orderStatus').innerText = 'Please fill out all fields correctly.';
        return;
    }

    // Simulate placing an order
    document.getElementById('orderStatus').innerText = `Order placed for ${quantity} x ${foodItem}.`;
});
// Initialize an array to store the orders
let orders = [];

// Load orders from localStorage if they exist
if (localStorage.getItem('orders')) {
    orders = JSON.parse(localStorage.getItem('orders'));
    displayOrders();
}

// Function to place an order
document.getElementById('orderForm').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get the values from the form
    const foodItem = document.getElementById("foodItem").value;
    const quantity = document.getElementById("quantity").value;

    // Create an order object
    const order = {
        foodItem: foodItem,
        quantity: quantity
    };

    // Add the order to the orders array
    orders.push(order);

    // Save the updated orders array to localStorage
    saveOrders();

    // Display the updated orders in the table
    displayOrders();

    // Reset the form
    document.getElementById('orderForm').reset();
});

// Function to display orders in the table
function displayOrders() {
    const orderTable = document.getElementById("orderTable");
    orderTable.innerHTML = "";

    orders.forEach((order, index) => {
        const row = orderTable.insertRow();
        row.insertCell(0).innerText = order.foodItem;
        row.insertCell(1).innerText = order.quantity;
        row.insertCell(2).innerHTML = `<button class="btn btn-warning btn-sm" onclick="editOrder(${index})">Edit</button> <button class="btn btn-danger btn-sm" onclick="deleteOrder(${index})">Delete</button>`;
    });
}

// Function to save the orders array to localStorage
function saveOrders() {
    localStorage.setItem('orders', JSON.stringify(orders));
}

// Function to delete an order
function deleteOrder(index) {
    orders.splice(index, 1);
    saveOrders();
    displayOrders();
}

// Function to edit an order (just a placeholder for now)
function editOrder(index) {
    // Logic for editing can be implemented here
    alert("Edit functionality not implemented yet");
}

// Function to mark order completion
function completeOrder() {
    // Logic for completing the order (e.g., clearing the table or sending a confirmation)
    alert("Order completed!");
    orders = [];
    saveOrders();
    displayOrders();
}

const themes = [
    {
        background: "#1A1A2E",
        color: "#FFFFFF",
        primaryColor: "#0F3460"
    },
    {
        background: "#461220",
        color: "#FFFFFF",
        primaryColor: "#E94560"
    },
    {
        background: "#192A51",
        color: "#FFFFFF",
        primaryColor: "#967AA1"
    },
    {
        background: "#F7B267",
        color: "#000000",
        primaryColor: "#F4845F"
    },
    {
        background: "#F25F5C",
        color: "#000000",
        primaryColor: "#642B36"
    },
    {
        background: "#231F20",
        color: "#FFF",
        primaryColor: "#BB4430"
    }
];

// Function to set the theme and save it to localStorage
const setTheme = (theme) => {
    const root = document.querySelector(":root");

    // Apply the theme to the CSS variables
    root.style.setProperty("--background", theme.background);
    root.style.setProperty("--color", theme.color);
    root.style.setProperty("--primary-color", theme.primaryColor);
    root.style.setProperty("--glass-color", theme.glassColor);

    // Save the theme to localStorage
    localStorage.setItem("userTheme", JSON.stringify(theme));
};

// Function to load the saved theme from localStorage if it exists
const loadSavedTheme = () => {
    const savedTheme = localStorage.getItem("userTheme");
    if (savedTheme) {
        // Parse the saved theme and apply it
        setTheme(JSON.parse(savedTheme));
    }
};

// Function to display theme buttons and attach click handlers
const displayThemeButtons = () => {
    const btnContainer = document.querySelector(".theme-btn-container");

    themes.forEach((theme) => {
        const div = document.createElement("div");
        div.className = "theme-btn";
        div.style.cssText = `background: ${theme.background}; width: 25px; height: 25px`;
        btnContainer.appendChild(div);

        div.addEventListener("click", () => setTheme(theme));
    });
};



// Load the saved theme on page load
loadSavedTheme();
displayThemeButtons();


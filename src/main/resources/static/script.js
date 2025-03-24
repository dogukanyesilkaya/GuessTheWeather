document.addEventListener("DOMContentLoaded", () => {
    // Get all country elements in the SVG
    const countries = document.querySelectorAll(".country");

    // Event listener for each country click
    countries.forEach(country => {
        country.addEventListener("click", () => {
            // Get the country name from the data-name attribute
            const countryName = country.getAttribute("data-name");
            document.getElementById("country-name").innerHTML = `<h2>You clicked on: ${countryName}</h2>`;
        });
    });
});

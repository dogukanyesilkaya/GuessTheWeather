let randomtemp=""

async function getRandomTemperature() {
    try {
        const response = await fetch("http://localhost:8080/country-randomtemp");
        randomtemp = await response.text();
        return randomtemp;
    } catch (error) {
        console.error("Error fetching random temperature:", error);
        return null;
    }
}

async function loadRandomTemperature() {
    try {
        document.getElementById("randomtemp").textContent = `Random Temperature: ${randomtemp}`;
        return randomtemp;
    } catch (error) {
        console.error("Error fetching random temperature:", error);
        return null;
    }
}

document.getElementById("search").addEventListener("input", async function() {
    const code = this.value.trim();
    if (code === "") {
        document.getElementById("result").textContent = "";
        return;
    }

    try {
        const randomtemp = await getRandomTemperature();

        const response = await fetch(`http://localhost:8080/country?code=${encodeURIComponent(code)}`);

        await response.text().then(temperature => {
            const temperatureInt = parseInt(temperature);
            const randomtempInt = parseInt(randomtemp);
            const resultText = (randomtempInt-temperatureInt).toString();
            document.getElementById("result").textContent = resultText;
        })


    } catch (error) {
        document.getElementById("result").textContent = "Error fetching data";
    }
});

window.onload = loadRandomTemperature;

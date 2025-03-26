

function hideElements(){
    document.getElementById("search").value = "";
    document.getElementById("randomtemp").value = `Random Temperature: `;
    document.getElementById("result").textContent = "";
}

function setGuessLeftText(guessesLeft){
    document.getElementById("guessesleft").textContent = `You have ${guessesLeft} guesses left`;
}

function checkGuessAccuracy(guessesLeft,compareInt){
    if(compareInt === 0){
        //game won retry popup
        document.getElementById("popup").getElementsByTagName('p')[0].textContent = "Congrats you won the game!!";
        document.getElementById("popup").style.display = "block";
        document.getElementById("overlay").style.display = "block";
        return;
    }
    if(guessesLeft === 0){
        //game lost retry popup
        document.getElementById("popup").getElementsByTagName('p')[0].textContent = "You've lost the game.";
        document.getElementById("popup").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

}

function setGuesses(diff){
    switch(diff.toLowerCase()){
        case "easy":
            guessesLeft=5;
            break;
        case "moderate":
            guessesLeft=3;
            break;
        case "hard":
            guessesLeft=2;
            break;
        case "extreme":
            guessesLeft=1;
            break;
        default:
            return ;
    }

    setGuessLeftText(guessesLeft);
}

async function getRandomTemperature() {
    try {
        const response = await fetch("http://localhost:8080/country-randomtemp");
        const randomtemp = await response.text();
        document.getElementById("randomtemp").textContent = `Random Temperature: ${randomtemp}`;
        return randomtemp;
    } catch (error) {
        return null;
    }
}
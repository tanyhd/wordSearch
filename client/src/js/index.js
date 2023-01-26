import { Grid } from "./gid";
import { Word } from "./word";

const submitWordBtn = document.querySelector(".submit-word");
const words = new Word();

submitWordBtn.addEventListener("click", async () => {
    const grid = new Grid();
    const commaSeperatedWords = document.querySelector("#add-word").value;
    const wordList = commaSeperatedWords.split(",");
    const gridSize = document.querySelector("#grid-size").value;

    let result = await fetchGridInfo(gridSize, wordList);
    let wordInPuzzle = result.wordInPuzzle;
    let contentsArray = result.contents.map(content => content.split(''));

    grid.words = wordInPuzzle;
    grid.renderGrid(gridSize, contentsArray, wordInPuzzle);
    //words.displayWord(wordInPuzzle);

    console.log("Words In Puzzle: ", wordInPuzzle);
    console.log("Contents Array: ", contentsArray);
});

async function fetchGridInfo(gridSize, words) {
    let response = await fetch('./wordgrid', { //local run - 'http://localhost:8080/wordgrid'
        method: 'POST',
        body: JSON.stringify({
            size: gridSize,
            words: words
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    });
    let result = await response.json();
    return result;
}
import { Grid } from "./gid";
import { Word } from "./word";

const submitWordBtn = document.querySelector(".submit-word");
const submitWordBtnRandom = document.querySelector(".submit-word-Random")
const words = new Word();

submitWordBtn.addEventListener("click", async () => {
    const grid = new Grid();
    const commaSeperatedWords = document.querySelector("#add-word").value;
    const wordList = commaSeperatedWords.split(",");
    const gridSize = document.querySelector("#grid-size").value;

    let result = await fetchGridInfo(gridSize, wordList);
    resetTimer();
    let wordInPuzzle = result.wordInPuzzle;
    let contentsArray = result.contents.map(content => content.split(''));

    grid.words = wordInPuzzle;
    grid.renderGrid(gridSize, contentsArray, wordInPuzzle);
    startTimer();
});

submitWordBtnRandom.addEventListener("click", async () => {
    const grid = new Grid();

    let result = await fetchGridInfoRandom();
    resetTimer();
    let wordInPuzzle = result.wordInPuzzle;
    let gridSize = result.gridSize;
    let contentsArray = result.contents.map(content => content.split(''));

    grid.words = wordInPuzzle;
    grid.renderGrid(gridSize, contentsArray, wordInPuzzle);
    startTimer();
})

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

async function fetchGridInfoRandom() {
    let response = await fetch('./wordgrid/randomWordGrid', { //local run - 'http://localhost:8080/wordgrid/randomWordGrid'
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    });
    let result = await response.json();
    return result;
}
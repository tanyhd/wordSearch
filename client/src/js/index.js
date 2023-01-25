import { Grid } from "./gid";
import { Word } from "./word";

const GRID_SIZE = 20;
const WORDS = ["ONE", "TWO", "THREE"];

const submitWordBtn = document.querySelector(".submit-word");
const grid = new Grid();
const words = new Word();

submitWordBtn.addEventListener("click", async () => {
    let result = await fetchGridInfo(GRID_SIZE, WORDS);
    let wordInPuzzle = result.wordInPuzzle;
    let contentsArray = result.contents.map(content => content.split(''));

    grid.renderGrid(GRID_SIZE, contentsArray);
    words.displayWord(wordInPuzzle);

    //let flattenContentsArray = contentsArray.flat();
    //let contentsString = contentsArray.map(row => row.join(' ')).join('\n');
    console.log("Words In Puzzle: ", wordInPuzzle);
    console.log("Contents Array: ", contentsArray);
});

async function fetchGridInfo(gridSize, words) {
    let response = await fetch('http://localhost:8080/wordgrid', {
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
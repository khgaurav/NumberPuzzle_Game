import java.awt.*;

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}
	public int getHeight() {
		return 250;
	}
	public int getXPosition() {
		return 200;
	}
	public int getYPosition() {
		return 200;
	}
	public String getTitle(){
		return "Number Puzzle";
	}
	public int getShuffleButtonFontSize() {
		return 12;
	}
	public int getNumbersFontSize() {
		return 12;
	}
	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}
	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	// The following three methods have to be written by the participants...

	public int handleButtonClicked(NumberPuzzleGame game){
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();

		// Y co ordinates i.e row numbers are multiples of 46 and X co ordinates i.e. column nos are multiples of 47
		// So normal co ordinates are dervied and for a 4x4 matrix the clicked button location is calculated
		int buttonClickedId = (buttonClicked.getY()/47) * 4 + buttonClicked.getX()/46;

		//Checking if the clicked button is in the same row or the same column to empty cell and adjacent to the empty cell
		if((buttons[emptyCellId].getX() == buttonClicked.getX() && (buttons[emptyCellId].getY() == buttonClicked.getY() + 47 || buttons[emptyCellId].getY() == buttonClicked.getY() - 47)) || (buttons[emptyCellId].getY() == buttonClicked.getY() && (buttons[emptyCellId].getX() == buttonClicked.getX() + 46 || buttons[emptyCellId].getX() == buttonClicked.getX() - 46))) {
			swapButton(buttons[emptyCellId], buttonClicked);
			System.out.println(buttonClickedId+"\t"+emptyCellId);
			emptyCellId = buttonClickedId;
		}
		
		return emptyCellId;
	}
	public int[] getRandomNumbersForGrid() {
		int arr[] = new int[15];
		int j = 0;
		while (j < 15) {
			int a = getRandomNumber() % 15 + 1; // Generating number between 1 and 15
			boolean present = false;
			for (int i = 0; i < j; i++) {
				// Checking if the generated number is already present
				if(arr[i] == a) {
					present = true;
					break;
				}
			}
			if(!present) {
				arr[j] = a;
				j++;
			}
		}
		
		return arr;
	}
	public boolean checkForWinner(Button[] buttons)
	{
		boolean winner = true;
		int arr[] = getIntegerArrayOfButtonIds(buttons);
		for(int i = 0; i <= 15; i++) {
			if(arr[i] != i + 1){
				winner = false;
				break;
			}
		}
		return winner;
	}
}
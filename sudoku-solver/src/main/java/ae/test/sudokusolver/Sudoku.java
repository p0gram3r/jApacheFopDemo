package ae.test.sudokusolver;

public class Sudoku {

    public static class SudokuBuilder {
        private Sudoku puzzle;

        public SudokuBuilder() {
            puzzle = new Sudoku();
        }

        public SudokuBuilder initField(int x, int y, int value) {
            puzzle.fields[x][y].setValue(value);
            return this;
        }

        public Sudoku buildPuzzle() {
            // TODO return new instance
            return puzzle;
        }
    }

    private Field[][] fields;

    public Sudoku() {
        fields = new Field[9][9];

        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                fields[i][j] = new Field();
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 9; y += 1) {
            for (int x = 0; x < 9; x += 1) {
                sb.append(fields[x][y]);
                sb.append(" ");
                if (x % 3 == 2) {
                    sb.append(" ");
                }
            }
            if (y % 3 == 2) {
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // TODO find faster implementation
    public boolean isSolved() {
        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                if (fields[i][j].getValue() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Field getField(int x, int y) {
        return fields[x][y];
    }

}
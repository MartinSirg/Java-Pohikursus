
/*

Martin Sirg (masirg, 179563IADB)

*/
package ee.ttu.iti0202.gomoku.strategies;

import ee.ttu.iti0202.gomoku.game.Location;
import ee.ttu.iti0202.gomoku.game.SimpleBoard;
import ee.ttu.iti0202.gomoku.opponent.ComputerStrategy;

import java.util.*;

/**
 * Important!
 * This is an example strategy class.
 * You should not overwrite this.
 * Instead make your own class, for example:
 * public class AgoStrategy implements ComputerStrategy
 *
 * and add all the logic there. The created strategy
 * should be visible under player selection automatically.
 *
 * This file here might be overwritten in future versions.
 *
 */
public class MartinStrategy implements ComputerStrategy {

    private List<Integer> buffer = new ArrayList<>();
    /**
     +Open three       - 100     - {0 1 1 1 0}
     +Capped three     - 10      - {-1 1 1 1 0 0} || {0 1 1 1 -1}
     +Capped three wall- 10      - {1 1 1 0 0}
     Gapped three      - 100     - {0 1 1 0 1} || {0 1 0 1 1} || {1 1 0 1 0} || {1 0 1 1 0} || {1 0 1 0 1}
     +Open four        - 1000    - {0 1 1 1 1 0}
     +Capped four      - 105     - {-1 1 1 1 1 0} || {0 1 1 1 1 -1}
     +Capped four wall - 105     - {wall 1 1 1 1 0}
     Gapped four       - 105     - { 1 1 1 0 1 } || { 1 1 0 1 1 } || { 1 0 1 1 1 }
     +Five-in-row      - 10000   - { 1 1 1 1 1 }
     */

    private static List<Integer> openFourNormal = List.of(new Integer[]{0, 1, 1, 1, 1, 0});

    private List<Integer> openThreeNormal = List.of(new Integer[]{0, 1, 1, 1, 0});
    private List<Integer> cappedThreeWallsNormal = List.of(new Integer[]{1, 1, 1, 0, 0});
    private List<Integer> cappedThree1Normal = List.of(new Integer[]{-1, 1, 1, 1, 0, 0});
    private List<Integer> cappedThree2Normal = List.of(new Integer[]{0, 1, 1, 1, -1});
    private List<Integer> gappedThree1Normal = List.of(new Integer[]{0, 1, 1, 0, 1});
    private List<Integer> gappedThree2Normal = List.of(new Integer[]{0, 1, 0, 1, 1});
    private List<Integer> gappedThree3Normal = List.of(new Integer[]{1, 1, 0, 1, 0});
    private List<Integer> gappedThree4Normal = List.of(new Integer[]{1, 0, 1, 1, 0});
    private List<Integer> gappedThree5Normal = List.of(new Integer[]{1, 0, 1, 0, 1});

    private List<Integer> cappedFour1Normal = List.of(new Integer[]{-1, 1, 1, 1, 1, 0});
    private List<Integer> cappedFour2Normal = List.of(new Integer[]{0, 1, 1, 1, 1, -1});
    private List<Integer> cappedFourWallsNormal = List.of(new Integer[]{1, 1, 1, 1, 0});
    private List<Integer> gappedFour1Normal = List.of(new Integer[]{1, 1, 1, 0, 1});
    private List<Integer> gappedFour2Normal = List.of(new Integer[]{1, 1, 0, 1, 1});
    private List<Integer> gappedFour3Normal = List.of(new Integer[]{1, 0, 1, 1, 1});

    private List<Integer> fiveInRowNormal = List.of(new Integer[]{1, 1, 1, 1, 1});




    private List<Integer> openThreeOpposite = List.of(new Integer[]{0, -1, -1, -1, 0});
    private List<Integer> cappedThreeWallsOpposite = List.of(new Integer[]{-1, -1, -1, 0, 0});
    private List<Integer> cappedThree1Opposite = List.of(new Integer[]{1, -1, -1, -1, 0, 0});
    private List<Integer> cappedThree2Opposite = List.of(new Integer[]{0, -1, -1, -1, 1});
    private List<Integer> gappedThree1Opposite = List.of(new Integer[]{0, -1, -1, 0, -1});
    private List<Integer> gappedThree2Opposite = List.of(new Integer[]{0, -1, 0, -1, -1});
    private List<Integer> gappedThree3Opposite = List.of(new Integer[]{-1, -1, 0, -1, 0});
    private List<Integer> gappedThree4Opposite = List.of(new Integer[]{-1, 0, -1, -1, 0});
    private List<Integer> gappedThree5Opposite = List.of(new Integer[]{-1, 0, -1, 0, -1});

    private List<Integer> openFourOpposite = List.of(new Integer[]{0, -1, -1, -1, -1, 0});
    private List<Integer> cappedFour1Opposite = List.of(new Integer[]{1, -1, -1, -1, -1, 0});
    private List<Integer> cappedFour2Opposite = List.of(new Integer[]{0, -1, -1, -1, -1, 1});
    private List<Integer> cappedFourWallsOpposite = List.of(new Integer[]{-1, -1, -1, -1, 0});
    private List<Integer> gappedFour1Opposite = List.of(new Integer[]{-1, -1, -1, 0, -1});
    private List<Integer> gappedFour2Opposite = List.of(new Integer[]{-1, -1, 0, -1, -1});
    private List<Integer> gappedFour3Opposite = List.of(new Integer[]{-1, 0, -1, -1, -1});

    private List<Integer> fiveInRowOpposite = List.of(new Integer[]{-1, -1, -1, -1, -1});

    @Override
    public Location getMove(SimpleBoard board, int player) {
        // let's operate on 2-d array
        /*
        int[][] b = board.getBoard();
        for (int row = b.length - 1; row >= 0; row--) {
            for (int col = b[0].length - 1; col >= 0; col--) {
                if (b[row][col] == SimpleBoard.EMPTY) {
                    // first empty location
                    return new Location(row, col);
                }
            }
        }
        */
//        int score2 = evaluateWholeBoard(board, SimpleBoard.PLAYER_BLACK);
//        System.out.println("Biggest streak from last white move: " + evaluateLastPlayerMove(board, SimpleBoard.PLAYER_WHITE));
//        System.out.println("Biggest streak from last black move: " + evaluateLastPlayerMove(board, SimpleBoard.PLAYER_BLACK));
//        System.out.println("Last move by white:" + board.getLastMove(SimpleBoard.PLAYER_WHITE));
//        System.out.println("Last move by black:" + board.getLastMove(SimpleBoard.PLAYER_BLACK));
        List<Location> locationsToTry = getLocationsToTry(board);
//        System.out.println(locationsToTry);
        if (locationsToTry.size() == 0) return Location.of(4, 4);
        if (locationsToTry.size() == 1) return Location.of(locationsToTry.get(0).getRow(), locationsToTry.get(0).getColumn());
        Map<Location, Integer> scores = new LinkedHashMap<>();

        for (Location location: locationsToTry) {
            board.setLocation(location.getRow(), location.getColumn(), player);
            scores.put(location, minimax(board, 1, player, false));
            board.setLocation(location.getRow(), location.getColumn(), 0);
        }
        Location biggestScoreLocation = null;
        for (Map.Entry<Location, Integer> entry: scores.entrySet()) {
            if (biggestScoreLocation == null) biggestScoreLocation = entry.getKey();
            if (scores.get(biggestScoreLocation) < entry.getValue()) biggestScoreLocation = entry.getKey();
        }
//        System.out.println("TOP LEVEL PICKING, I WANT HIGHEST");
//        System.out.println(scores);
//        System.out.println("I AM PICKING : " + biggestScoreLocation);
        return biggestScoreLocation;

//        for (int row = board.getHeight() - 1; row >= 0; row--) {
//            for (int col = board.getWidth() - 1; col >= 0; col--) {
//                if (board.isEmpty(row, col)) {
//                    // first empty location
//                    return Location.of(row, col);
//                }
//            }
//        }
//
//        return null;
    }
    private int minimax(SimpleBoard board, int depth, int player, boolean maximize) {
        int enemy = (player == 1) ? -1: 1;
        if (depth == 0) {
            //Double enemyScore = evaluateSectionOfBoard(board, enemy) * 1.1;
            return evaluateSectionOfBoard(board, player) - evaluateSectionOfBoard(board, enemy);
        }
        List<Location> locations = getLocationsToTry(board);
        Map<Location, Integer> scores = new LinkedHashMap<>();

        if (maximize) { //maximize
            for (Location location: locations) {
                board.setLocation(location.getRow(), location.getColumn(), player);
                scores.put(location, minimax(board, depth - 1, player, false));
                board.setLocation(location.getRow(), location.getColumn(), 0);
            }
//            System.out.println("I AM PLAYER, I WANT HIGHEST SCORE");
//            System.out.println(scores);
            Location biggestScore = null;
            for (Map.Entry<Location, Integer> entry: scores.entrySet()) {
                if (biggestScore == null) biggestScore = entry.getKey();
                if (scores.get(biggestScore) <= entry.getValue()) biggestScore = entry.getKey();
            }
//            System.out.println("I AM PICKING : " + biggestScore);
            return scores.get(biggestScore);

        } else { //minimize
            for (Location location: locations) {
                board.setLocation(location.getRow(), location.getColumn(), enemy);
                scores.put(location, minimax(board, depth - 1, player, true));
                board.setLocation(location.getRow(), location.getColumn(), 0);
            }
//            System.out.println("I AM ENEMY, I WANT LOWEST SCORE");
//            System.out.println(scores);
            Location smallestScore = null;
            for (Map.Entry<Location, Integer> entry: scores.entrySet()) {
                if (smallestScore == null) smallestScore = entry.getKey();
                if (scores.get(smallestScore) > entry.getValue()) smallestScore = entry.getKey();
            }
//            System.out.println("I AM PICKING: " + smallestScore);
            return scores.get(smallestScore);
        }

    }

    private int evaluateLastPlayerMove(SimpleBoard board, int playerToEvaluate) {
        int horizontal = playerToEvaluate, vertical = playerToEvaluate,
                primaryDiagonal = playerToEvaluate, secondaryDiagonal = playerToEvaluate;

        Location loc;
        if (board.getLastMove(playerToEvaluate).isPresent()) {
            loc = board.getLastMove(playerToEvaluate).get();
        } else {
            return -1;
        }

        //left
        for (int col = loc.getColumn() - 1; col >= 0; col--) {
            if (board.getLocation(loc.getRow(), col) == playerToEvaluate) {
                horizontal += playerToEvaluate;
            } else {
                break;
            }
        }
        //right
        for (int col = loc.getColumn() + 1; col < board.getWidth(); col++) {
            if (board.getLocation(loc.getRow(), col) == playerToEvaluate) {
                horizontal += playerToEvaluate;
            } else {
                break;
            }
        }
        //down
        for (int row = loc.getRow() + 1; row < board.getHeight(); row++) {
            if (board.getLocation(row, loc.getColumn()) == playerToEvaluate) {
                vertical += playerToEvaluate;
            } else {
                break;
            }
        }//up
        for (int row = loc.getRow() - 1; row >= 0; row--) {
            if (board.getLocation(row, loc.getColumn()) == playerToEvaluate) {
                vertical += playerToEvaluate;
            } else {
                break;
            }
        }
        // up-right row -, col +
        for (int row = loc.getRow() - 1, col = loc.getColumn() + 1; row >= 0 && col < board.getWidth(); row--, col++) {
            if (board.getLocation(row, col) == playerToEvaluate ) {
                secondaryDiagonal += playerToEvaluate;
            } else {
                break;
            }
        }
        // down-right row +, col +
        for (int row = loc.getRow() + 1, col = loc.getColumn() + 1; row < board.getHeight() && col < board.getWidth(); row++, col++) {
            if (board.getLocation(row, col) == playerToEvaluate ) {
                primaryDiagonal += playerToEvaluate;
            } else {
                break;
            }
        }
        // up-left row -, col -
        for (int row = loc.getRow() - 1, col = loc.getColumn() - 1; row >= 0 && col >= 0; row--, col--) {
            if (board.getLocation(row, col) == playerToEvaluate ) {
                primaryDiagonal += playerToEvaluate;
            } else {
                break;
            }
        }
        // down-left row +, col -
        for (int row = loc.getRow() + 1, col = loc.getColumn() - 1; row < board.getHeight() && col >= 0; row++, col--) {
            if (board.getLocation(row, col) == playerToEvaluate ) {
                secondaryDiagonal += playerToEvaluate;
            } else {
                break;
            }
        }
        vertical = Math.abs(vertical);
        horizontal = Math.abs(horizontal);
        primaryDiagonal = Math.abs(primaryDiagonal);
        secondaryDiagonal = Math.abs(secondaryDiagonal);

        return Math.max(Math.max(vertical, horizontal), Math.max(primaryDiagonal, secondaryDiagonal));

    }

    private List<Location> getLocationsToTry(SimpleBoard board) {
        List<Location> locationsToTry = new ArrayList<>();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (board.getLocation(row, col) != SimpleBoard.EMPTY) {
                    //try adding surrounding slots to locationsToTry
                    for (int i = row - 1; i < row + 2; i++) {
                        for (int j = col - 1; j < col + 2; j++) {
                            if (i < 0 || j < 0 || i >= board.getHeight() || j >= board.getWidth()) continue;
                            if (board.getLocation(i, j) != SimpleBoard.EMPTY) continue;
                            if (!locationsToTry.contains(Location.of(i, j))) locationsToTry.add(Location.of(i, j));
                        }
                    }
                }
            }
        }
        return locationsToTry;
    }

    private List<Location> getLocationsToEvaluate(SimpleBoard board) {
        List<Location> locationsToEvaluate = new ArrayList<>();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (board.getLocation(row, col) != SimpleBoard.EMPTY) {
                    //try adding surrounding slots to locationsToTry
                    for (int i = row - 1; i < row + 2; i++) {
                        for (int j = col - 1; j < col + 2; j++) {
                            if (i < 0 || j < 0 || i >= board.getHeight() || j >= board.getWidth()) continue;
                            if (!locationsToEvaluate.contains(Location.of(i, j))) {
                                locationsToEvaluate.add(Location.of(i, j));
                            }
                        }
                    }
                }
            }
        }
        return locationsToEvaluate;
    }

    private int evaluateLocation(SimpleBoard board, int row, int col, int player) {
        int score = 0;

        //CHECK OPEN FOURS
        //horizontal
        List<Integer> openThree, openFour, fiveInRow, cappedThreeWalls, cappedThree1, cappedThree2, cappedFour1,
                cappedFour2, cappedFourWalls, gappedThree1, gappedThree2, gappedThree3, gappedThree4, gappedThree5,
                gappedFour1, gappedFour2, gappedFour3;


        if (player == 1) {
            openThree = openThreeNormal;
            openFour = openFourNormal;
            fiveInRow = fiveInRowNormal;
            cappedThreeWalls = cappedThreeWallsNormal;
            cappedThree1 = cappedThree1Normal;
            cappedThree2 = cappedThree2Normal;
            cappedFour1 = cappedFour1Normal;
            cappedFour2 = cappedFour2Normal;
            cappedFourWalls = cappedFourWallsNormal;
            gappedThree1 = gappedThree1Normal;
            gappedThree2 = gappedThree2Normal;
            gappedThree3 = gappedThree3Normal;
            gappedThree4 = gappedThree4Normal;
            gappedThree5 = gappedThree5Normal;
            gappedFour1 = gappedFour1Normal;
            gappedFour2 = gappedFour2Normal;
            gappedFour3 = gappedFour3Normal;

        } else {
            openThree = openThreeOpposite;
            openFour = openFourOpposite;
            fiveInRow = fiveInRowOpposite;
            cappedThreeWalls = cappedThreeWallsOpposite;
            cappedThree1 = cappedThree1Opposite;
            cappedThree2 = cappedThree2Opposite;
            cappedFour1 = cappedFour1Opposite;
            cappedFour2 = cappedFour2Opposite;
            cappedFourWalls = cappedFourWallsOpposite;
            gappedThree1 = gappedThree1Opposite;
            gappedThree2 = gappedThree2Opposite;
            gappedThree3 = gappedThree3Opposite;
            gappedThree4 = gappedThree4Opposite;
            gappedThree5 = gappedThree5Opposite;
            gappedFour1 = gappedFour1Opposite;
            gappedFour2 = gappedFour2Opposite;
            gappedFour3 = gappedFour3Opposite;
        }

        //TODO: teineteist välistavad patternid if else lausetesse

        score += 10 * checkPattern(cappedThree1, board, row, col);
        score += 10 * checkPattern(cappedThree2, board, row, col);
        score += 100 * checkPattern(openThree, board, row, col);
        score += 100 * checkPattern(gappedThree1, board, row, col);
        score += 100 * checkPattern(gappedThree2, board, row, col);
        score += 100 * checkPattern(gappedThree3, board, row, col);
        score += 100 * checkPattern(gappedThree4, board, row, col);
        score += 100 * checkPattern(gappedThree5, board, row, col);
        score += 105 * checkPattern(cappedFour1, board, row, col);
        score += 105 * checkPattern(cappedFour2, board, row, col);
        score += 105 * checkPattern(gappedFour1, board, row, col);
        score += 105 * checkPattern(gappedFour2, board, row, col);
        score += 105 * checkPattern(gappedFour3, board, row, col);
        score += 1000 * checkPattern(fiveInRow, board, row, col);
        if (player == -1) {
            score += 100000 * checkPattern(openFour, board, row, col);
        } else {
            score += 10000 * checkPattern(openFour, board, row, col);
        }

        if (col == 0) {
            score += 10 * checkCappedPatternLeftWall(cappedThreeWalls, board, row, col);
            score += 105 * checkCappedPatternLeftWall(cappedFourWalls, board, row, col);
        }
        if (row == 0) {
            score += 10 * checkCappedPatternUpWall(cappedThreeWalls, board, row, col);
            score += 105 * checkCappedPatternUpWall(cappedFourWalls, board, row, col);
        }
        if (col == board.getWidth() - 1) {
            score += 10 * checkCappedPatternRightWall(cappedThreeWalls, board, row, col);
            score += 105 * checkCappedPatternRightWall(cappedFourWalls, board, row, col);
        }
        if (row == board.getHeight() - 1) {
            score += 10 * checkCappedPatternDownWall(cappedThreeWalls, board, row, col);
            score += 105 * checkCappedPatternDownWall(cappedFourWalls, board, row, col);
        }

        return score;
    }

    private int checkCappedPatternLeftWall(List<Integer> pattern, SimpleBoard board, int row, int col) {
        if (col > 0) {
            System.out.println("Got wrong input left wall");
            return 0;
        }
        int length = pattern.size() - 1;

        //check from left to right
        for (int i = 0; i <= length; i++) {
            buffer.add(board.getLocation(row, col + i));
        }
        if (pattern.equals(buffer)) {
//            System.out.println("Found capped at left wall");
            buffer.clear();
            return 1;
        }
        buffer.clear();
        return 0;
    }

    private int checkCappedPatternUpWall(List<Integer> pattern, SimpleBoard board, int row, int col) {
        if (row > 0) {
            System.out.println("Got wrong input up wall");
            return 0;
        }
        int length = pattern.size() - 1;

        //check from up to down
        for (int i = 0; i <= length; i++) {
            buffer.add(board.getLocation(row + i, col));
        }
        if (pattern.equals(buffer)) {
//            System.out.println("Found capped at up wall");
            buffer.clear();
            return 1;
        }
        buffer.clear();
        return 0;
    }

    private int checkCappedPatternRightWall(List<Integer> pattern, SimpleBoard board, int row, int col) {
        if (col < board.getWidth() - 1) {
            System.out.println("Got wrong input right wall");
            return 0;
        }
        int length = pattern.size() - 1;

        //check from right to left
        for (int i = 0; i <= length; i++) {
            buffer.add(board.getLocation(row, col - i));
        }
        if (pattern.equals(buffer)) {
//            System.out.println("Found capped at right wall");
            buffer.clear();
            return 1;
        }
        buffer.clear();
        return 0;
    }

    private int checkCappedPatternDownWall(List<Integer> pattern, SimpleBoard board, int row, int col) {
        if (row < board.getWidth() - 1) {
            System.out.println("Got wrong input down wall");
            return 0;
        }
        int length = pattern.size() - 1;

        //check from down to up
        for (int i = 0; i <= length; i++) {
            buffer.add(board.getLocation(row - i, col));
        }
        if (pattern.equals(buffer)) {
//            System.out.println("Found capped at down wall");
            buffer.clear();
            return 1;
        }
        buffer.clear();
        return 0;
    }

    private int checkPattern(List<Integer> pattern, SimpleBoard board, int row, int col) {
        int amount = 0, length = pattern.size() - 1;

        //horizontal
        for (int i = 0;  col + length< board.getWidth() && i <= length; i++) {
            buffer.add(board.getLocation(row, col + i));
        }
        if (pattern.equals(buffer)) amount += 1;
        buffer.clear();

        //vertical
        for (int i = 0; row + length < board.getHeight() && i <= length; i++) {
            buffer.add(board.getLocation(row + i, col));
        }
        if (pattern.equals(buffer)) amount += 1;
        buffer.clear();

        //primary Diagonal
        for (int i = 0; row + length < board.getHeight()  && col + length < board.getWidth() && i <= length; i++) {
            buffer.add(board.getLocation(row + i, col + i));
        }
        if (pattern.equals(buffer)) amount += 1;
        buffer.clear();

        //secondary Diagonal
        for (int i = 0; col - length >= 0 && row + length < board.getHeight() && i <= length; i++) {
            buffer.add(board.getLocation(row + i, col - i));
        }
        if (pattern.equals(buffer)) amount += 1;
        buffer.clear();

        return amount;
    }

    private int evaluateWholeBoard(SimpleBoard board, int player) {
        int score = 0;
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                score += evaluateLocation(board, row, col, player);
            }
        }
        return score;
    }

    private int evaluateSectionOfBoard(SimpleBoard board, int player) {
        List<Location> locations = getLocationsToEvaluate(board);
        int score = 0;
        for (Location location: locations) {
            score += evaluateLocation(board, location.getRow(), location.getColumn(), player);
        }
        return score;
    }

    private void printBoard(SimpleBoard board) {
        for (int row = 0; row < board.getHeight(); row++) {
            StringBuilder sb = new StringBuilder();
            sb.append(" |");
            for (int col = 0; col < board.getWidth(); col++) {
                sb.append(String.format("% d", board.getLocation(row, col))).append(" | ");
            }
            System.out.println(sb.toString());
        }
    }


    @Override
    public String getName() {
        return "Martin Sirg";
    }
}

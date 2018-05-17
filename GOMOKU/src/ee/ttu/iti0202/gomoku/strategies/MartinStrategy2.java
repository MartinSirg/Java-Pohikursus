package ee.ttu.iti0202.gomoku.strategies;

import ee.ttu.iti0202.gomoku.game.Location;
import ee.ttu.iti0202.gomoku.game.SimpleBoard;
import ee.ttu.iti0202.gomoku.opponent.ComputerStrategy;

import java.util.*;

/**
 *Debugged and working:
 * getLocationsToEvaluate()
 * getLocationsToTry()
 * patterns
 *
 */
public class MartinStrategy2 implements ComputerStrategy {

    @Override
    public Location getMove(SimpleBoard board, int player) {

        List<Location> possibleMoves = getLocationsToTry(board);
        if (possibleMoves.isEmpty()) return Location.of(9, 9);

        /**
         * System.out.println(getScoreFromState(board, player));
         * patternite debuggimiseks luba siin olev print ja minimaxi terminal panna returnima 0
         */
        int bestScore = Integer.MIN_VALUE, alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;
        Location bestMove = null;
        for (Location move : possibleMoves) {
            board.setLocation(move, player);
            int score = getEnemyScore(board, 1 ,alpha, beta, player, move);
            board.setLocation(move, SimpleBoard.EMPTY);
            if (score > bestScore) {
                bestMove = move;
                bestScore = score;
            }
            if (score > alpha) alpha = score;
        }
        if (board.getLocation(bestMove) != 0) System.out.println("ILLEGEAL MOVE");
        return bestMove;
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

    private int getPlayerScore(SimpleBoard board, int depth, int alpha, int beta, int player, Location location) {
        if (depth == 0) return getScoreFromState(board, player);
        int bestScore = Integer.MIN_VALUE;
        List<Location> possibleMoves = getLocationsToTry(board);
        for (Location move: possibleMoves) {
            board.setLocation(move, player);
            int score = getEnemyScore(board, depth - 1, alpha, beta, player, move);
            board.setLocation(move, SimpleBoard.EMPTY);
            if (score > bestScore) bestScore = score;
            if (score >= beta) return bestScore;
            if (score > alpha) alpha = score;
        }
        return bestScore;
    }

    private int getEnemyScore(SimpleBoard board, int depth, int alpha, int beta, int player, Location location) {
        if (depth == 0) return getScoreFromState(board, player);
        int bestScore = Integer.MAX_VALUE;
        List<Location> possibleMoves = getLocationsToTry(board);
        for (Location move: possibleMoves) {
            board.setLocation(move, -player);
            int score = getPlayerScore(board, depth - 1, alpha, beta, player, move);
            board.setLocation(move, SimpleBoard.EMPTY);
            if (score < bestScore) bestScore = score;
            if (score <= alpha) return score;
            if (score < beta) beta = score;

        }
        return bestScore;
    }

    private int getScoreFromState(SimpleBoard board, int player) {
        int playerScore = 0;
        int enemyScore = 0;
        for (Location location: getLocationsToEvaluate(board)) {
            playerScore += evaluateLocation(board, location, player, false);
            enemyScore += evaluateLocation(board, location, -player, true);
        }
        return  2 * playerScore - enemyScore;
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
                            if (!locationsToEvaluate.contains(Location.of(i, j))) locationsToEvaluate.add(Location.of(i, j));
                        }
                    }
                }
            }
        }
        return locationsToEvaluate;
    }

    private int evaluateLocation(SimpleBoard board, Location location, int player, boolean isEnemy) {
        int enemy = -player, score = 0, col = location.getColumn(), row = location.getRow();
        int multiplier = 1;
        if (isEnemy) multiplier = 10;

        List<Integer> openThree = List.of(new Integer[]{0, player, player, player, 0});
        List<Integer> cappedThree1 = List.of(new Integer[]{enemy, player, player, player, 0, 0});
        List<Integer> cappedThree2 = List.of(new Integer[]{0, player, player, player, enemy});
        List<Integer> gappedThree1 = List.of(new Integer[]{0, player, player, 0, player});
        List<Integer> gappedThree2 = List.of(new Integer[]{0, player, 0, player, player});
        List<Integer> gappedThree3 = List.of(new Integer[]{player, player, 0, player, 0});
        List<Integer> gappedThree4 = List.of(new Integer[]{player, 0, player, player, 0});
        List<Integer> gappedThree5 = List.of(new Integer[]{player, 0, player, 0, player});
        List<Integer> gappedThree6 = List.of(new Integer[]{player, 0, 0, player, player});
        List<Integer> gappedThree7 = List.of(new Integer[]{player, player, 0, 0, player});
        List<Integer> openFour = List.of(new Integer[]{0, player, player, player, player, 0});
        List<Integer> cappedFour1 = List.of(new Integer[]{enemy, player, player, player, player, 0});
        List<Integer> cappedFour2 = List.of(new Integer[]{0, player, player, player, player, enemy});
        List<Integer> gappedFour1 = List.of(new Integer[]{player, player, player, 0, player});
        List<Integer> gappedFour2 = List.of(new Integer[]{player, player, 0, player, player});
        List<Integer> gappedFour3 = List.of(new Integer[]{player, 0, player, player, player});
        List<Integer> fiveInRow = List.of(new Integer[]{player, player, player, player, player});


        List<Integer> cappedThreeWalls = List.of(new Integer[]{player, player, player, 0, 0});
        List<Integer> cappedFourWalls = List.of(new Integer[]{player, player, player, player, 0});
        List<Integer> cappedThreeWallsRev = List.of(new Integer[]{0, 0, player, player, player});
        List<Integer> cappedFourWallsRev = List.of(new Integer[]{0, player, player, player, player});

        if (col == 0) { // Left wall
            score += 10000 * checkPatternHorizontal(cappedThreeWalls, board, row, col);
            score += 100050 * checkPatternHorizontal(cappedFourWalls, board, row, col);
        }
        if (row == 0) { // Upper wall
            score += 10000 * checkPatternVertical(cappedThreeWalls, board, row, col);
            score += 100050 * checkPatternVertical(cappedFourWalls, board, row, col);
        }
        if (row == 0 && col == 0) { // Upper-Left corner
            score += 10000 * checkPatternPrimDiag(cappedThreeWalls, board, row, col);
            score += 100050 * checkPatternPrimDiag(cappedFourWalls, board, row, col);
        }
        if (row == 0 && col == board.getWidth() - 1) { // Upper-Right corner
            score += 10000 * checkPatternSecDiag(cappedThreeWalls, board, row, col);
            score += 100050 * checkPatternSecDiag(cappedFourWalls, board, row, col);
        }


        if (row == board.getHeight() - 1) { // Lower wall
            score += 10000 * checkPatternVertical(cappedThreeWallsRev, board, board.getHeight() - 5, col);
            score += 100050 * checkPatternVertical(cappedFourWallsRev, board, board.getHeight() - 5, col);
        }
        if (col == board.getWidth() - 1) { // Right Wall
            score += 10000 * checkPatternHorizontal(cappedThreeWallsRev, board, row, board.getWidth() - 5);
            score += 100050 * checkPatternHorizontal(cappedFourWallsRev, board, row, board.getWidth() - 5);
        }
        if (row == board.getHeight() - 1 && col == 0) { // Lower-Left corner
            score += 10000 * checkPatternSecDiag(cappedThreeWallsRev, board, board.getHeight() -5, 4);
            score += 100050 * checkPatternSecDiag(cappedFourWallsRev, board, board.getHeight() -5, 4);
        }
        if (row == board.getHeight() - 1 && col == board.getWidth() - 1) { // Lower-right corner
            score += 10000 * checkPatternPrimDiag(cappedThreeWallsRev, board, board.getHeight() -5, board.getWidth() - 5);
            score += 100050 * checkPatternPrimDiag(cappedFourWallsRev, board, board.getHeight() -5, board.getWidth() - 5);
        }

        score += 100000 * checkPattern(openThree, board, row, col);
        score += 10000 * checkPattern(cappedThree1, board, row, col);
        score += 10000 * checkPattern(cappedThree2, board, row, col);
        score += 100000 * checkPattern(gappedThree1, board, row, col);
        score += 100000 * checkPattern(gappedThree2, board, row, col);
        score += 100000 * checkPattern(gappedThree3, board, row, col);
        score += 100000 * checkPattern(gappedThree4, board, row, col);
        score += 100000 * checkPattern(gappedThree5, board, row, col);
        score += 100000 * checkPattern(gappedThree6, board, row, col);
        score += 100000 * checkPattern(gappedThree7, board, row, col);
        score += 1000000 * checkPattern(openFour, board, row, col) * multiplier;
        score += 100050 * checkPattern(cappedFour1, board, row, col) * multiplier;
        score += 100050 * checkPattern(cappedFour2, board, row, col) * multiplier;
        score += 100050 * checkPattern(gappedFour1, board, row, col) * multiplier;
        score += 100050 * checkPattern(gappedFour2, board, row, col) * multiplier;
        score += 100050 * checkPattern(gappedFour3, board, row, col) * multiplier;
        score += 10000000 * checkPattern(fiveInRow, board, row, col) * multiplier;

        return score;

    }

    private int checkPattern(List<Integer> pattern, SimpleBoard board, int row, int col) {
//        Counts pattern occurrences from all directions from one point
        int count = 0;
        count += checkPatternHorizontal(pattern, board, row, col);
        count += checkPatternVertical(pattern, board, row, col);
        count += checkPatternPrimDiag(pattern, board, row, col);
        count += checkPatternSecDiag(pattern, board, row, col);
        if (count > 1) return 10;
        return count;
    }

    private int checkPatternVertical(List<Integer> pattern, SimpleBoard board, int row, int col) {
        int length = pattern.size() - 1;

        //Vertical Up->Down

        if (row + length < board.getHeight()) {
            for (int i = 0; i <= length; i++) {
                if (!pattern.get(i).equals(board.getLocation(row + i, col))) {
                    return 0;
                }
            }
//            System.out.println("Found vertical " + pattern );
            return 1;
        }
//        System.out.println("Pattern too long");
        return 0;
    }

    private int checkPatternHorizontal(List<Integer> pattern, SimpleBoard board, int row, int col) {
        int length = pattern.size() - 1;

        //Horizontal Left->Right

        if (col + length < board.getWidth()) {
            for (int i = 0; i <= length; i++) {
                if (pattern.get(i) != board.getLocation(row, col + i)) return 0;
            }
//            System.out.println("Found horizontal " + pattern);
            return 1;
        }
//        System.out.println("Pattern too long");
        return 0;
    }

    private int checkPatternPrimDiag(List<Integer> pattern, SimpleBoard board, int row, int col) {
        int length = pattern.size() - 1;

        //Primary Diagonal UpLeft->DownRight

        if (row + length < board.getHeight()  && col + length < board.getWidth()) {
            for (int i = 0; i <= length; i++) {
                if (!pattern.get(i).equals(board.getLocation(row + i, col + i))) return 0;
            }
//            System.out.println("Found Primary diagonal " + pattern);
            return 1;
        }
//        System.out.println("Pattern too long");
        return 0;

    }

    private int checkPatternSecDiag(List<Integer> pattern, SimpleBoard board, int row, int col) {
        int length = pattern.size() - 1;

        //Secondary Diagonal UpRight->DownLeft

        if (col - length >= 0 && row + length < board.getHeight()) {
            for (int i = 0; col - length >= 0 && row + length < board.getHeight() && i <= length; i++) {
                if (!pattern.get(i).equals(board.getLocation(row + i, col - i))) return 0;
            }
//            System.out.println("Found secondary diagonal " + pattern);
            return 1;
        }
//        System.out.println("Pattern too long");
        return 0;

    }

//    private int getScoreFromState2(SimpleBoard board, int player, Location location) {
//        int playerScore = evaluateLocation3(board, board.getLastMove(-player).get() , player, false);
//        int enemyScore = evaluateLocation3(board, board.getLastMove(-player).get(), -player, true);
////        System.out.println("Player: " + playerScore);
////        System.out.println("Enemy:  " + enemyScore);
//        return playerScore - 3 * enemyScore;
//    }
//
//    private List<Location> simplifiedEvaluationLocations(List<Location> locations, SimpleBoard board) {
//        List<Location> result = new ArrayList<>();
//        for (Location location: locations) {
//            if (board.getLocation(location) != 0) {
//                int current = board.getLocation(location);
//                int row = location.getRow(), col = location.getColumn();
//                boolean N = false, S = false, W = false, E = false, NW = false, NE = false, SE = false, SW = false;
//                for (int i = 0; i < 5; i++) {
//                    if (getLocation(board, row + i, col) == -current || getLocation(board, row + i, col) == -666) S = true;
//                    if (getLocation(board, row - i, col) == -current || getLocation(board, row - i, col) == -666) N = true;
//                    if (getLocation(board, row, col + i) == -current || getLocation(board, row, col + i) == -666) E = true;
//                    if (getLocation(board, row, col - i) == -current || getLocation(board, row, col - i) == -666) W = true;
//                    if (getLocation(board, row + i, col + i) == -current || getLocation(board, row + i, col + i) == -666) SE = true;
//                    if (getLocation(board, row - i, col - i) == -current || getLocation(board, row - i, col - i) == -666) NW = true;
//                    if (getLocation(board, row + i, col - i) == -current || getLocation(board, row + i, col - i) == -666) SW = true;
//                    if (getLocation(board, row - i, col + i) == -current || getLocation(board, row - i, col + i) == -666) NE = true;
//                    if (N && S && W && E && NE && NW && SE && SW) break;
//                }
//                if (!(N && S && W && E && NE && NW && SE && SW)) result.add(location);
//            }
//        }
//        return result;
//    }
//
//    private int getLocation(SimpleBoard board, int row, int col) {
//        if (row >= 0 && col >= 0 && row < board.getHeight() && col < board.getWidth()) {
//            return board.getLocation(row, col);
//        } else {
//            return -666;
//        }
//    }
//
//    private List<Location> getAllLocations(SimpleBoard board) {
//        List<Location> locations = new ArrayList<>();
//        for (int row = 0; row < board.getHeight(); row++) {
//            for (int col = 0; col < board.getWidth(); col++) {
//                locations.add(Location.of(row, col));
//            }
//        }
//        return locations;
//    }
//
//    private int evaluateLocation2(SimpleBoard board, Location location, int player, boolean isEnemy) {
//        int enemy = -player, score = 0, col = location.getColumn(), row = location.getRow();
//        int multiplier = 1;
//        if (isEnemy) multiplier = 10;
//
//        List<Integer> openThree = List.of(new Integer[]{0, player, player, player, 0});
//        List<Integer> cappedThree1 = List.of(new Integer[]{enemy, player, player, player, 0, 0});
//        List<Integer> cappedThree2 = List.of(new Integer[]{0, player, player, player, enemy});
//        List<Integer> openFour = List.of(new Integer[]{0, player, player, player, player, 0});
//        List<Integer> cappedFour1 = List.of(new Integer[]{enemy, player, player, player, player, 0});
//        List<Integer> cappedFour2 = List.of(new Integer[]{0, player, player, player, player, enemy});
//        List<Integer> fiveInRow = List.of(new Integer[]{player, player, player, player, player});
//
//
//        List<Integer> gappedThree3 = List.of(new Integer[]{player, player, 0, player, 0});
//        List<Integer> gappedThree4 = List.of(new Integer[]{player, 0, player, player, 0});
//
//
//        List<Integer> right = getPatternHorizontal(6, board, row, col);
//        List<Integer> down = getPatternVertical(6, board, row, col);
//        List<Integer> downRight = getPatternPrimDiag(6, board, row, col);
//        List<Integer> downLeft = getPatternSecDiag(6, board, row, col);
//
//        List<List<Integer>> boardPatterns = List.of(right, down, downLeft, downRight);
//
//        for (List<Integer> boardPattern: boardPatterns) {
//            if (boardPattern.size() != 0) {
//                boardPattern = boardPattern.subList(0, 5);
//                if (boardPattern.equals(openThree)) {
////                    System.out.println("Open three " + location);
//                    score += 100000;
//                } else if (boardPattern.equals(cappedThree2)) {
//                    score += 10000;
////                    System.out.println("Capped three(5) " + location);
//                } else if (boardPattern.equals(fiveInRow)) {
//                    score += 1000000 * multiplier;
////                    System.out.println("Five in row " + location);
//                } else {
//                    List<Integer> sublist = boardPattern.subList(1,4);
//                    if (boardPattern.get(0) == player && boardPattern.get(4) == player) {
//                        if (!boardPattern.subList(1, 4).contains(enemy)) {
//                            int zeros = 0; int playerButtons = 0;
//                            for (int i: sublist) {
//                                if (i == 0) zeros++;
//                                if (i == player) playerButtons++;
//                            }
//                            if (zeros == 1 && playerButtons == 2) {
//                                score += 100050; // gapped four (i.e. 11011)
////                                System.out.println("Gapped four " + location);
//                            }
//                            if (zeros == 2 && playerButtons == 1) {
//                                score += 100000; // gapped three (i.e. 10011)
////                                System.out.println("Gapped three 1" + location);
//                            }
//                        }
//                    } else if (boardPattern.get(0) == player && boardPattern.get(4) == 0) {
//                        if (boardPattern.equals(gappedThree3) || boardPattern.equals(gappedThree4)) {
//                            score += 100000;
////                            System.out.println("Gapped three 2" + location);
//                        }
//                    }
//                }
//            }
//        }
//        for (List<Integer> boardPattern: boardPatterns) {
//            if (boardPattern.size() != 0) {
//                if (cappedThree1.equals(boardPattern)) {
//                    score += 10000;
////                    System.out.println("Capped Three(6) " + location);
//                } else if (openFour.equals(boardPattern)) {
//                    score += 1000000 * multiplier;
////                    System.out.println("Open four " + location);
//                } else if (cappedFour1.equals(boardPattern) || cappedFour2.equals(boardPattern)) {
//                    score += 100050;
////                    System.out.println("Capped four(6) " + location);
//                }
//            }
//        }
//
//
//
//        List<Integer> cappedThreeWalls = List.of(new Integer[]{player, player, player, 0, 0});
//        List<Integer> cappedFourWalls = List.of(new Integer[]{player, player, player, player, 0});
//        List<Integer> cappedThreeWallsRev = List.of(new Integer[]{0, 0, player, player, player});
//        List<Integer> cappedFourWallsRev = List.of(new Integer[]{0, player, player, player, player});
//
//
//        if (col == 0) { // Left wall
//            if (right.subList(0,5).equals(cappedFourWalls)) {
//                score += 100050;
////                System.out.println("Left wall capped-4");
//            }
//            if (right.subList(0,5).equals(cappedThreeWalls)) {
//                score += 10000;
////                System.out.println("Left wall capped-3");
//            }
//        }
//        if (row == 0) { // Upper wall
//            if (down.subList(0,5).equals(cappedFourWalls)) {
//                score += 100050;
////                System.out.println("Upper wall capped-4");
//            }
//            if (down.subList(0,5).equals(cappedThreeWalls)) {
//                score += 10000;
////                System.out.println("Upper wall capped-3");
//            }
//        }
//        if (row == 0 && col == 0) { // Upper-Left corner
//            if (downRight.subList(0,5).equals(cappedFourWalls)) {
//                score += 100050;
////                System.out.println("Upper-Left capped-4");
//            }
//            if (downRight.subList(0,5).equals(cappedThreeWalls)) {
//                score += 10000;
////                System.out.println("Upper-Left capped-3");
//            }
//        }
//        if (row == 0 && col == board.getWidth() - 1 ) { // Upper-Right corner
//            if (downLeft.subList(0,5).equals(cappedFourWalls)) {
//                score += 100050;
////                System.out.println("Upper-Right capped-3");
//            }
//            if (downLeft.subList(0,5).equals(cappedThreeWalls)) {
//                score += 10000;
////                System.out.println("Upper-Right capped-3");
//            }
//        }
//
//
//        if (row == board.getHeight() - 1) { // Lower wall
//            List<Integer> shiftedDown = getPatternVertical(5, board, board.getHeight() - 5, col);
//            if (shiftedDown.equals(cappedThreeWallsRev)) {
//                score += 10000;
////                System.out.println("Lower wall capped-3");
//            }
//            if (shiftedDown.equals(cappedFourWallsRev)) {
//                score += 100050;
////                System.out.println("Lower wall capped-4");
//            }
//        }
//        if (col == board.getWidth() - 1) { // Right Wall
//            List<Integer> shiftedLeft = getPatternHorizontal(5, board, row, board.getWidth() - 5);
//            if (shiftedLeft.equals(cappedThreeWallsRev)) {
//                score += 10000;
////                System.out.println("Right wall capped-3");
//            }
//            if (shiftedLeft.equals(cappedFourWallsRev)) {
//                score += 100050;
////                System.out.println("Right wall capped-4");
//            }
//        }
//        if (row == board.getHeight() - 1 && col == 0) { // Lower-Left corner
//            List<Integer> shiftedDownLeft = getPatternSecDiag(5, board, board.getHeight() - 5, 4);
//            if (shiftedDownLeft.equals(cappedThreeWallsRev)) {
//                score += 10000;
////                System.out.println("Lower-Left capped-3");
//            }
//            if (shiftedDownLeft.equals(cappedFourWallsRev)) {
//                score += 100050;
////                System.out.println("Lower-Left capped-4");
//            }
//        }
//        if (row == board.getHeight() - 1 && col == board.getWidth() - 1) { // Lower-right corner
//            List<Integer> shiftedDownRight = getPatternPrimDiag(5, board, board.getHeight() - 5, board.getWidth() - 5);
//            if (shiftedDownRight.equals(cappedThreeWallsRev)) {
//                score += 10000;
////                System.out.println("Lower-Right capped-3");
//            }
//            if (shiftedDownRight.equals(cappedFourWallsRev)) {
////                System.out.println("Lower-Right capped-4");
//                score += 100050;
//            }
//        }
//
//
//        return score;
//
//    }
//
//    private int evaluateLocation3(SimpleBoard board, Location location, int player, boolean isEnemy) {
//        int enemy = -player, score = 0;
//        int row = location.getRow(), col = location.getColumn();
//
//        List<Integer> openThree = List.of(new Integer[]{0, player, player, player, 0});
//        List<Integer> cappedThree1 = List.of(new Integer[]{enemy, player, player, player, 0, 0});
//        List<Integer> cappedThree2 = List.of(new Integer[]{0, 0, player, player, player, enemy});
//        List<Integer> gappedThree1 = List.of(new Integer[]{0, player, player, 0, player});
//        List<Integer> gappedThree2 = List.of(new Integer[]{0, player, 0, player, player});
//        List<Integer> gappedThree3 = List.of(new Integer[]{player, player, 0, player, 0});
//        List<Integer> gappedThree4 = List.of(new Integer[]{player, 0, player, player, 0});
//        List<Integer> gappedThree5 = List.of(new Integer[]{player, 0, player, 0, player});
//        List<Integer> gappedThree6 = List.of(new Integer[]{player, 0, 0, player, player});
//        List<Integer> gappedThree7 = List.of(new Integer[]{player, player, 0, 0, player});
//        List<Integer> openFour = List.of(new Integer[]{0, player, player, player, player, 0});
//        List<Integer> cappedFour1 = List.of(new Integer[]{enemy, player, player, player, player, 0});
//        List<Integer> cappedFour2 = List.of(new Integer[]{0, player, player, player, player, enemy});
//        List<Integer> gappedFour1 = List.of(new Integer[]{player, player, player, 0, player});
//        List<Integer> gappedFour2 = List.of(new Integer[]{player, player, 0, player, player});
//        List<Integer> gappedFour3 = List.of(new Integer[]{player, 0, player, player, player});
//        List<Integer> fiveInRow = List.of(new Integer[]{player, player, player, player, player});

//
//
//        List<Integer> cappedThreeWalls = List.of(new Integer[]{player, player, player, 0, 0});
//        List<Integer> cappedFourWalls = List.of(new Integer[]{player, player, player, player, 0});
//        List<Integer> cappedThreeWallsRev = List.of(new Integer[]{0, 0, player, player, player});
//        List<Integer> cappedFourWallsRev = List.of(new Integer[]{0, player, player, player, player});
//
////        List<List<Integer>> patternsLen6 = List.of(openFour, cappedThree1, cappedThree2, cappedFour1, cappedFour2);
////
////        List<List<Integer>> patternsLen5 = List.of(openThree, gappedThree1,
////                gappedThree2, gappedThree3, gappedThree4, gappedThree4, gappedThree5, gappedThree6, gappedThree7,
////                gappedFour1, gappedFour2, gappedFour3, fiveInRow);
//
//        //Main center check
//
//
//        //Primary Diagonal
//        for (int row1 = row, col1 = col; row1 > row - 6 && row1 >= 0 && col1 >= 0; row1--, col1--) {
////            System.out.println("Primary diag");
//            if (board.getHeight() - row1 >= 6 && board.getWidth() - col1 >= 6) {
//                score += 1000000 * checkPatternPrimDiag(openFour, board, row1, col1);
////                score += 10000 * checkPatternPrimDiag(cappedThree1, board, row1, col1);
////                score += 10000 * checkPatternPrimDiag(cappedThree2, board, row1, col1);
////                score += 100050 * checkPatternPrimDiag(cappedFour1, board, row1, col1);
////                score += 100050 * checkPatternPrimDiag(cappedFour2, board, row1, col1);
//            }
//            if (board.getHeight() - row1 >= 5 && board.getWidth() - col1 >= 5) {
//                //check 5 patterns
//                score += 100000 * checkPatternPrimDiag(openThree, board, row1, col1);
////                score += 10000 * checkPatternPrimDiag(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree2, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree3, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree4, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree5, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree6, board, row1, col1);
////                score += 100000 * checkPatternPrimDiag(gappedThree7, board, row1, col1);
////                score += 100050 * checkPatternPrimDiag(gappedFour1, board, row1, col1);
////                score += 100050 * checkPatternPrimDiag(gappedFour2, board, row1, col1);
////                score += 100050 * checkPatternPrimDiag(gappedFour3, board, row1, col1);
//                score += 10000000 * checkPatternPrimDiag(fiveInRow, board, row1, col1);
//            }
//            if (row1 == 0 && col1 == 0) {
//                score += 10000 * checkPatternPrimDiag(cappedThreeWalls, board, row1, col1);
//                score += 100050 * checkPatternPrimDiag(cappedFourWalls, board, row1, col1);
//            }
//            if (row1 == board.getHeight() - 5 && col1 == board.getWidth() - 5) {
////                score += 10000 * checkPatternPrimDiag(cappedThreeWallsRev, board, row1, col1);
////                score += 100050 * checkPatternPrimDiag(cappedFourWallsRev, board, row1, col1);
//            }
//        }
//
//        //Secondary Diagonal
//        for (int row1 = row, col1 = col; row1 > row - 6 && row1 >= 0 && col1 < board.getWidth(); row1--, col1++) {
////            System.out.println("Secondary diag");
//            if (board.getHeight() - row1 >= 6 && col1 >= 5) {
//                score += 1000000 * checkPatternSecDiag(openFour, board, row1, col1);
////                score += 10000 * checkPatternSecDiag(cappedThree1, board, row1, col1);
////                score += 10000 * checkPatternSecDiag(cappedThree2, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(cappedFour1, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(cappedFour2, board, row1, col1);
//            }
//            if (board.getHeight() - row1 >= 5 &&  col1 >= 4) {
//                //check 5 patterns
//                score += 100000 * checkPatternSecDiag(openThree, board, row1, col1);
////                score += 10000 * checkPatternSecDiag(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree2, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree3, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree4, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree5, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree6, board, row1, col1);
////                score += 100000 * checkPatternSecDiag(gappedThree7, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(gappedFour1, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(gappedFour2, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(gappedFour3, board, row1, col1);
//                score += 10000000 * checkPatternSecDiag(fiveInRow, board, row1, col1);
//            }
//            if (row1 == 0 && col1 == board.getWidth() - 1) {
////                score += 10000 * checkPatternSecDiag(cappedThreeWalls, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(cappedFourWalls, board, row1, col1);
//            }
//            if (row1 == board.getHeight() - 5 && col1 == 4) {
////                score += 10000 * checkPatternSecDiag(cappedThreeWallsRev, board, row1, col1);
////                score += 100050 * checkPatternSecDiag(cappedFourWallsRev, board, row1, col1);
//            }
//        }
//
//        //Horizontal
//        for (int row1 = row, col1 = col; col1 > col - 6 && col1 >= 0; col1--) {
////            System.out.println("Horizontal");
//            if (board.getWidth() - col1 >= 6) {
//                score += 1000000 * checkPatternHorizontal(openFour, board, row1, col1);
////                score += 10000 * checkPatternHorizontal(cappedThree1, board, row1, col1);
////                score += 10000 * checkPatternHorizontal(cappedThree2, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(cappedFour1, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(cappedFour2, board, row1, col1);
//            }
//            if (board.getWidth() - col1 >= 5) {
//                //check 5 patterns
//                score += 100000 * checkPatternHorizontal(openThree, board, row1, col1);
////                score += 10000 * checkPatternHorizontal(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree2, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree3, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree4, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree5, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree6, board, row1, col1);
////                score += 100000 * checkPatternHorizontal(gappedThree7, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(gappedFour1, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(gappedFour2, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(gappedFour3, board, row1, col1);
//                score += 10000000 * checkPatternHorizontal(fiveInRow, board, row1, col1);
//            }
//            if (col1 == 0) {
////                score += 10000 * checkPatternHorizontal(cappedThreeWalls, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(cappedFourWalls, board, row1, col1);
//            }
//            if (col1 == board.getWidth() - 5) {
////                score += 10000 * checkPatternHorizontal(cappedThreeWallsRev, board, row1, col1);
////                score += 100050 * checkPatternHorizontal(cappedFourWallsRev, board, row1, col1);
//            }
//        }
//
//        //Vertical col = const
//        for (int row1 = row, col1 = col; row1 > row - 6 && row1 >= 0; row1--) {
////            System.out.println("Vertical " + row + " " + col);
//            if (board.getHeight() - row1 >= 6) {
//                score += 1000000 * checkPatternVertical(openFour, board, row1, col1);
////                score += 10000 * checkPatternVertical(cappedThree1, board, row1, col1);
////                score += 10000 * checkPatternVertical(cappedThree2, board, row1, col1);
////                score += 100050 * checkPatternVertical(cappedFour1, board, row1, col1);
////                score += 100050 * checkPatternVertical(cappedFour2, board, row1, col1);
//            }
//            if (board.getHeight() - row1 >= 5) {
//                //check 5 patterns
//                score += 100000 * checkPatternVertical(openThree, board, row1, col1);
////                score += 10000 * checkPatternVertical(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree1, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree2, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree3, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree4, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree5, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree6, board, row1, col1);
////                score += 100000 * checkPatternVertical(gappedThree7, board, row1, col1);
////                score += 100050 * checkPatternVertical(gappedFour1, board, row1, col1);
////                score += 100050 * checkPatternVertical(gappedFour2, board, row1, col1);
////                score += 100050 * checkPatternVertical(gappedFour3, board, row1, col1);
//                score += 10000000 * checkPatternVertical(fiveInRow, board, row1, col1);
//            }
////            if (row1 == 0) {
////                score += 10000 * checkPatternVertical(cappedThreeWalls, board, row1, col1);
////                score += 100050 * checkPatternVertical(cappedFourWalls, board, row1, col1);
////            }
////            if (row1 == board.getHeight() - 5) {
////                score += 10000 * checkPatternVertical(cappedThreeWallsRev, board, row1, col1);
////                score += 100050 * checkPatternVertical(cappedFourWallsRev, board, row1, col1);
////            }
//        }
//        return score;
//    }
//
//    private int evaluateLocation4(SimpleBoard board, Location location, int player, boolean isEnemy) {
//        int enemy = -player, score = 0;
//        int row = location.getRow(), col = location.getColumn();
//
//        List<Integer> openThree = List.of(new Integer[]{0, player, player, player, 0});
//        List<Integer> cappedThree1 = List.of(new Integer[]{enemy, player, player, player, 0, 0});
//        List<Integer> cappedThree2 = List.of(new Integer[]{0, 0, player, player, player, enemy});
//        List<Integer> gappedThree1 = List.of(new Integer[]{0, player, player, 0, player});
//        List<Integer> gappedThree2 = List.of(new Integer[]{0, player, 0, player, player});
//        List<Integer> gappedThree3 = List.of(new Integer[]{player, player, 0, player, 0});
//        List<Integer> gappedThree4 = List.of(new Integer[]{player, 0, player, player, 0});
//        List<Integer> gappedThree5 = List.of(new Integer[]{player, 0, player, 0, player});
//        List<Integer> gappedThree6 = List.of(new Integer[]{player, 0, 0, player, player});
//        List<Integer> gappedThree7 = List.of(new Integer[]{player, player, 0, 0, player});
//        List<Integer> openFour = List.of(new Integer[]{0, player, player, player, player, 0});
//        List<Integer> cappedFour1 = List.of(new Integer[]{enemy, player, player, player, player, 0});
//        List<Integer> cappedFour2 = List.of(new Integer[]{0, player, player, player, player, enemy});
//        List<Integer> gappedFour1 = List.of(new Integer[]{player, player, player, 0, player});
//        List<Integer> gappedFour2 = List.of(new Integer[]{player, player, 0, player, player});
//        List<Integer> gappedFour3 = List.of(new Integer[]{player, 0, player, player, player});
//        List<Integer> fiveInRow = List.of(new Integer[]{player, player, player, player, player});
//
//
//
//        List<Integer> cappedThreeWalls = List.of(new Integer[]{player, player, player, 0, 0});
//        List<Integer> cappedFourWalls = List.of(new Integer[]{player, player, player, player, 0});
//        List<Integer> cappedThreeWallsRev = List.of(new Integer[]{0, 0, player, player, player});
//        List<Integer> cappedFourWallsRev = List.of(new Integer[]{0, player, player, player, player});
//
////        List<List<Integer>> patternsLen6 = List.of(openFour, cappedThree1, cappedThree2, cappedFour1, cappedFour2);
////
////        List<List<Integer>> patternsLen5 = List.of(openThree, gappedThree1,
////                gappedThree2, gappedThree3, gappedThree4, gappedThree4, gappedThree5, gappedThree6, gappedThree7,
////                gappedFour1, gappedFour2, gappedFour3, fiveInRow);
//
//        //Main center check
//
//
//        //Primary Diagonal
//        for (int row1 = row, col1 = col; row1 > row - 6 && row1 >= 0 && col1 >= 0; row1--, col1--) {
//            System.out.println("Prim diag: " + row1 + " : " + col1);
//        }
//
//        //Secondary Diagonal
//        for (int row1 = row, col1 = col; row1 > row - 6 && row1 >= 0 && col1 < board.getWidth(); row1--, col1++) {
//            System.out.println("Sec diag: " + row1 + " : " + col1);
//        }
//
//        //Horizontal
//        for (int row1 = row, col1 = col; col1 > col - 6 && col1 >= 0; col1--) {
//            System.out.println("Hor: " + row1 + " : " + col1);
//        }
//
//        //Vertical col = const
//        for (int row1 = row, col1 = col; row1 > row - 6 && row1 >= 0; row1--) {
//            System.out.println("Vert: " + row1 + " : " + col1);
//        }
//        return score;
//    }
//
//
//    private List<Integer> getPatternVertical(int len, SimpleBoard board, int row, int col) {
//        int length = len - 1;
//        List<Integer> result = new ArrayList<>();
//
//        //Vertical Up->Down
//
//        if (row + length < board.getHeight()) {
//            for (int i = 0; i <= length; i++) {
//                result.add(board.getLocation(row + i, col));
//            }
//        }
//        return result;
//    }
//
//    private List<Integer> getPatternHorizontal(int len, SimpleBoard board, int row, int col) {
//        int length = len - 1;
//        List<Integer> result = new ArrayList<>();
//
//        //Horizontal Left->Right
//
//        if (col + length < board.getWidth()) {
//            for (int i = 0; i <= length; i++) {
//                result.add(board.getLocation(row, col + i));
//            }
//        }
//        return result;
//    }
//
//    private List<Integer> getPatternPrimDiag(int len, SimpleBoard board, int row, int col) {
//        int length = len - 1;
//        List<Integer> result = new ArrayList<>();
//        //Primary Diagonal UpLeft->DownRight
//
//        if (row + length < board.getHeight()  && col + length < board.getWidth()) {
//            for (int i = 0; i <= length; i++) {
//                result.add(board.getLocation(row + i, col + i));
//            }
//        }
//        return result;
//
//    }
//
//    private List<Integer> getPatternSecDiag(int len, SimpleBoard board, int row, int col) {
//        int length = len - 1;
//        List<Integer> result = new ArrayList<>();
//
//        //Secondary Diagonal UpRight->DownLeft
//
//        if (col - length >= 0 && row + length < board.getHeight()) {
//            for (int i = 0; i <= length; i++) {
//                result.add(board.getLocation(row + i, col - i));
//            }
//        }
//        return result;
//
//    }
//
//
//    private void printBoard(SimpleBoard board) {
//        for (int row = 0; row < board.getHeight(); row++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(" |");
//            for (int col = 0; col < board.getWidth(); col++) {
//                sb.append(String.format("% d", board.getLocation(row, col))).append(" | ");
//            }
//            System.out.println(sb.toString());
//        }
//    }
//



    @Override
    public String getName() {
        return "Martin Sirg2";
    }

}


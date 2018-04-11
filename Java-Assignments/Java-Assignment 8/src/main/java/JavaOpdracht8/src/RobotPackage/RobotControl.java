package RobotPackage;

public class RobotControl {

    public static void main(String[] args){
        RobotControl MainFrame = new RobotControl();
        Robot MyRobot1 = new Robot();
        Robot MyRobot2 = new Robot(0,2,"West");

        /* example commands
        System.out.println(MyRobot2.getState());
        System.out.println(MyRobot2.getCoordinates()[0]+", "+MyRobot2.getCoordinates()[1]);
        MyRobot2.turnLeft();
        MyRobot2.moveForward(2);
        MyRobot2.moveBackward();
        MyRobot2.turnRight();
        MyRobot2.Execute();
        System.out.println(MyRobot2.getState());
        System.out.println(MyRobot2.getCoordinates()[0]+", "+MyRobot2.getCoordinates()[1]);
        */
    }

}

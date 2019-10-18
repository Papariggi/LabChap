package Command;

import battleMap.BattleMap;

import java.util.Stack;

public class Invoker
{
    private Stack <AllCommands> undoStack = new Stack<>();
    private Stack <AllCommands> redoStack = new Stack<>();
    private BattleMap battleMap;

   public Invoker(BattleMap battleMap)
   {
       this.battleMap = battleMap;
   }

   public void invoke(AllCommands command){
       command.makeMove();
       undoStack.push(command);
       redoStack.clear();
   }

    public void undo()
    {
        AllCommands commands = redoStack.pop();
        commands.undo();
        redoStack.push(commands);
    }

    public void redo()
    {
        AllCommands commands = redoStack.pop();
        commands.redo();
        redoStack.push(commands);
    }

    public void move()
    {
        invoke(new OnlyOneMoveCommand(battleMap));
    }
}

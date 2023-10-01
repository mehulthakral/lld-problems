package model;

import exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private String commandName;
    private List<String> params;

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(String inputLine) {
        List<String> paramsList = Arrays.stream(inputLine.trim().split(" ")).filter(param -> param.length() > 0)
                .collect(Collectors.toList());
        if (paramsList.isEmpty()) {
            throw new InvalidCommandException();
        }
        this.commandName = paramsList.get(0);
        paramsList.remove(0);
        this.params = paramsList;
    }
}

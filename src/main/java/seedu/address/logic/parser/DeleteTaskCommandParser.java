package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.NoSuchElementException;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code DeleteTaskCommand} object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteTaskCommand}
     * and returns a {@code DeleteTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_TASK);

        String name;
        String group;
        String task;

        try {
            name = argMultimap.getPreamble();
            group = argMultimap.getValue(PREFIX_GROUP).get();
            task = argMultimap.getValue(PREFIX_TASK).get();
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), e);
        }

        return new DeleteTaskCommand(new Name(name), group, new Assignment(task));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Person;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;


public class DeleteUserByNameCommand extends Command {
    public static final String COMMAND_WORD = "deletebyname";

    public static final String MESSAGE_USAGE = COMMAND_WORD
    + ": Deletes the person identified by name of the person in the displayed person list.\n"
    + "Parameters: NAME (must be exactly the same as person's name)\n"
    + "Example: " + COMMAND_WORD + " UserName ";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final FullNamePredicate predicate;

    public DeleteUserByNameCommand(FullNamePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();

        int targetIndex = -1;
        for (int i = 0; i < lastShownList.size(); i++) {
            Person currentPerson = lastShownList.get(i);
            if (predicate.test(currentPerson)) {
                targetIndex = i;
                break;
            }
        }
        if (targetIndex == -1) {
            return new CommandResult(String.format(Messages.MESSAGE_INVALID_PERSON_NAME));
        }

        Person personToDelete = lastShownList.get(targetIndex);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteUserByNameCommand)) {
            return false;
        }

        // state check
        DeleteUserByNameCommand e = (DeleteUserByNameCommand) other;
        return predicate.equals((e.predicate));

    }







}

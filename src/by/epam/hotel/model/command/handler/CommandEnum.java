package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.handler.navigation.*;

public enum CommandEnum {
    /**
     * Enumeration of all commands.
     * Handling of commands
     * is in {@code execute()} methods of classes-handlers
     */
    LANGUAGE{
        {
            this.command = new LanguageCommand();
        }
    },
    SIGNUP{
        {
            this.command = new SignUpCommand();
        }
    },

    OK_SIGNUP{
        {
            this.command = new OkSignUpCommand();
        }
    },
    GOTO_MAIN{
        {
            this.command = new GoToMainCommand();
        }
    },
    GOTO_ROOMS{
        {
            this.command = new GoToRoomsCommand();
        }
    },
    GOTO_CONTACTS{
        {
            this.command = new GoToOrdersCommand();
        }
    },
    GOTO_ABOUTUS{
        {
            this.command = new GoToAboutusCommand();
        }
    },
    RESERVE{
        {
            this.command = new ReserveCommand();
        }
    },
    OK_RESERVE{
        {
            this.command = new OkReserveCommand();
        }
    },
    PAY{
        {
            this.command = new PayCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },
    CHECK_ORDER{
        {
            this.command = new CheckOrderCommand();
        }
    },
    SEND_ANSWER{
        {
            this.command = new SendAnswerCommand();
        }
    },
    ENTER {
        {
            this.command = new EnterCommand();
        }
    };



    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}

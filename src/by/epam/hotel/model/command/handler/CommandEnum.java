package by.epam.hotel.model.command.handler;


import by.epam.hotel.model.command.handler.navigation.*;

public enum CommandEnum {
    /**
     * Enumeration of all commands
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
            this.command = new GoToContactsCommand();
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
    LOGOUT{
        {
            this.command = new LogoutCommand();
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

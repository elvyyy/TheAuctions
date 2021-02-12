package com.epam.auctions.command;

import com.epam.auctions.command.impl.CancelLotCommand;
import com.epam.auctions.command.impl.CheckUserExistingCommand;
import com.epam.auctions.command.impl.ConfirmLotCommand;
import com.epam.auctions.command.impl.CreateLotCommand;
import com.epam.auctions.command.impl.DefaultCommand;
import com.epam.auctions.command.impl.EditLotCommand;
import com.epam.auctions.command.impl.GetAdminPanel;
import com.epam.auctions.command.impl.GetCreateLotCommand;
import com.epam.auctions.command.impl.GetLoginCommand;
import com.epam.auctions.command.impl.GetLotCommand;
import com.epam.auctions.command.impl.GetLotsCommand;
import com.epam.auctions.command.impl.GetNonVerifiedLots;
import com.epam.auctions.command.impl.GetProfileCommand;
import com.epam.auctions.command.impl.GetSignUpCommand;
import com.epam.auctions.command.impl.GetUserLotsCommand;
import com.epam.auctions.command.impl.LoadMoreCommand;
import com.epam.auctions.command.impl.LoginCommand;
import com.epam.auctions.command.impl.LogoutCommand;
import com.epam.auctions.command.impl.MakeBidCommand;
import com.epam.auctions.command.impl.SearchCommand;
import com.epam.auctions.command.impl.SetLocaleCommand;
import com.epam.auctions.command.impl.SignupCommand;
import com.epam.auctions.command.impl.UpdateLotCommand;
import com.epam.auctions.command.impl.UpdateUserProfile;
import com.epam.auctions.command.impl.UpdateUserRoleCommand;
import com.epam.auctions.command.impl.UpdateUserStatusCommand;
import com.epam.auctions.service.impl.AuctionServiceImpl;
import com.epam.auctions.service.impl.LotBidServiceImpl;
import com.epam.auctions.service.impl.LotServiceImpl;
import com.epam.auctions.service.impl.UserServiceImpl;

public enum CommandType {
    GET_SIGNUP(new GetSignUpCommand()),
    GET_LOGIN(new GetLoginCommand()),
    SIGNUP(new SignupCommand(UserServiceImpl.INSTANCE)),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    SET_LOCALE(new SetLocaleCommand()),
    EXISTS(new CheckUserExistingCommand(UserServiceImpl.INSTANCE)),
    GET_LOTS(new GetLotsCommand(LotServiceImpl.INSTANCE)),
    GET_CREATE_LOT(new GetCreateLotCommand()),
    CREATE_LOT(new CreateLotCommand(LotServiceImpl.INSTANCE)),
    GET_NON_VERIFIED_LOTS(new GetNonVerifiedLots(LotServiceImpl.INSTANCE)),
    GET_USER_LOTS(new GetUserLotsCommand(LotServiceImpl.INSTANCE)),
    CANCEL_LOT(new CancelLotCommand(LotServiceImpl.INSTANCE)),
    LOAD_MORE(new LoadMoreCommand(LotServiceImpl.INSTANCE)),
    GET_PROFILE(new GetProfileCommand()),
    UPDATE_USER_PROFILE(new UpdateUserProfile(UserServiceImpl.INSTANCE)),
    GET_ADMIN_PANEL(new GetAdminPanel()),
    UPDATE_USER_STATUS(new UpdateUserStatusCommand(UserServiceImpl.INSTANCE)),
    UPDATE_USER_ROLE(new UpdateUserRoleCommand(UserServiceImpl.INSTANCE)),
    EDIT_LOT(new EditLotCommand(LotServiceImpl.INSTANCE)),
    UPDATE_LOT(new UpdateLotCommand(LotServiceImpl.INSTANCE)),
    SEARCH(new SearchCommand(LotServiceImpl.INSTANCE)),
    CONFIRM_LOT(new ConfirmLotCommand(LotServiceImpl.INSTANCE, AuctionServiceImpl.INSTANCE)),
    GET_LOT(new GetLotCommand(LotServiceImpl.INSTANCE, UserServiceImpl.INSTANCE)),
    MAKE_BID(new MakeBidCommand(LotServiceImpl.INSTANCE));

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getCommandName() {
        return name();
    }
}

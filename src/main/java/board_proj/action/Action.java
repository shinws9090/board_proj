package board_proj.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public interface Action {

	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException ;
	
}

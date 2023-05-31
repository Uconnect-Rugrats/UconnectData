package uco.doo.rugrats.uconnect.data.dao.relational;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.utils.Messages;
import uco.doo.rugrats.uconnect.utils.UtilSql;

public abstract class SqlDAO<E> {
	private Connection connection;
	
	protected SqlDAO(final Connection connection) {
		setConnection(connection);
	}
	
	protected final Connection getConnection() {
		return connection;
	}
	
	private final void setConnection(final Connection connection) {
		if (!UtilSql.connectionIsOpen(connection)) {
			throw UconnectDataException.create(Messages.SqlDAO.TECHNICAL_MESSAGE_CONNECTION_NOT_OPEN, Messages.SqlDAO.USER_MESSAGE_CONNECTION_NOT_OPEN);
		}		
		this.connection = connection;
	}
	
	protected abstract String preparedSelect();
	
	protected abstract String preparedFrom();
	
	protected abstract String preparedWhere(E entity, List<Object> parameter);
	
	protected abstract String preparedOrderBy();
	
	protected abstract void setParameters(PreparedStatement preparedStatement, List<Object> parameters);
	
	protected abstract List<E> executeQuery(PreparedStatement preparedStatement);
}

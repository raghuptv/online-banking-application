package org.thothlab.devilsvault.dao.transaction;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thothlab.devilsvault.db.model.Transaction;

@Repository ("TransactionSpecificDao")
public class InternalTransactionDaoImpl extends TransactionDaoImpl {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);	
		super.setDataSource(dataSource);
	}
	public int createTransaction() {
		String query = "INSERT INTO transaction_pending (payer_id, payee_id,amount, hashvalue,transaction_type,description,status,approver,critical,timestamp_created,timestamp_updated) values (?,?,?,?,?,?,?,?,?,?,?)";
		int rowsAffected = jdbcTemplate.update(query, 1, 1,500, "pending", "Add money","Add 1500USD","sds","sdsds",true,"2016-10-10","2016-10-10");
	    return rowsAffected;
	}
	public List<Transaction> getAllPending() {
		
		String query = "SELECT * FROM transaction_pending";
		List<Transaction> transactionList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Transaction>(Transaction.class));
		return transactionList;
		/*Transaction transaction = new Transaction();
		List <Transaction> transactionList = new ArrayList();
		String query = "SELECT *FROM transaction_pending";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ResultSet out = ps.executeQuery(query);
			while(out.next()) 
			{
			transaction.setId((int)out.getObject(1));
			transaction.setPayer_id((int)out.getObject(2));
			transaction.setPayee_id((int)out.getObject(3));
			transaction.setAmount((BigDecimal)out.getObject(4));
			transaction.setHashvalue((out.getObject(5)).toString());
			transaction.setTransaction_type((out.getObject(6)).toString());
			transaction.setDescription((out.getObject(7)).toString());
			transaction.setStatus((out.getObject(8)).toString());
			transaction.setApprover((out.getObject(9)).toString());
			transaction.setCritical((boolean)out.getObject(10));
			transaction.setTimestamp_created(out.getDate(11));
			transaction.setTimestamp_updated(out.getDate(12));
			transactionList.add(transaction);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return transactionList;*/
	}

	public List<Transaction> getAllCompleted() {
		String query = "SELECT * FROM transaction_completed";
		List<Transaction> transactionList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Transaction>(Transaction.class));
		return transactionList;
	}
	@Override
	public Boolean save(Transaction transaction, String type) {
		
		return super.save(transaction, type);
	}
	
	public List <Transaction> getByUserId(int id, String table)
    {
        String query = "select *from " + table + " where payer_id = '" + id + "' OR payee_id = '" + id + "';";
        List<Transaction> transactionList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Transaction>(Transaction.class));
        return transactionList;
    }
	
    public void approveTransaction(int id, String type) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM "+ type +" WHERE id =" + id;
        List<Transaction> transactionList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Transaction>(Transaction.class));
        Transaction transaction = transactionList.get(0);
        transaction.setStatus("approved");
        save(transaction, "transaction_completed");
        deleteById(id, type);
    }
    
    public void rejectTransaction(int id, String type) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM "+ type +" WHERE id ="+ id;
        List<Transaction> transactionList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Transaction>(Transaction.class));
        Transaction transaction = transactionList.get(0);
        transaction.setStatus("rejected");
        save(transaction, "transaction_completed");
        deleteById(id, type);
    }

	
}

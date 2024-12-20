package tw.brad.stest2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tw.brad.stest2.model.Member;
import tw.brad.stest2.model.Response;
import tw.brad.utils.BCrypt;

/*
 * NamedParameterJdbcTemplate
 * update() => Insert, Delete, Update
 * query() => Select
 */

@RestController
@RequestMapping("/brad01")
public class Brad01 {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private Response response;
	
	@PostMapping("/member")
	public Response insert(@RequestBody @Valid Member member) {
		String sql = "INSERT INTO member (account,passwd,realname) VALUES (:account, :passwd, :realname)";
		Map<String, String> data = new HashMap<String, String>();
		data.put("account", member.getAccount());
		data.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
		data.put("realname", member.getRealname());
		
		namedParameterJdbcTemplate.update(sql, data);
		
		response.setError(0);
		response.setMesg("Success");
		
		return response;
	}
	
	@DeleteMapping("/member/{id}")
	public Response delete(@PathVariable Integer id) {
		String sql = "DELETE FROM member WHERE id = :id";
		Map<String, Integer> data = new HashMap<>();
		data.put("id", id);
		
		int n = namedParameterJdbcTemplate.update(sql, data);
		if (n > 0) {
			response.setError(0);
			response.setMesg("Success");
		}else {
			response.setError(1);
			response.setMesg("NO Delete");
		}
		
		return response;
	}
	
	
}

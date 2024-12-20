package tw.brad.stest2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.stest2.model.Member;
import tw.brad.stest2.model.Response;

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
	public Response insert(@RequestBody Member member) {
		String sql = "INSERT INTO member (account,passwd,realname) VALUES (:account, :passwd, :realname)";
		Map<String, String> data = new HashMap<String, String>();
		data.put("account", member.getAccount());
		data.put("passwd", member.getPasswd());
		data.put("realname", member.getRealname());
		
		namedParameterJdbcTemplate.update(sql, data);
		
		response.setError(0);
		response.setMesg("Success");
		
		return response;
	}
	
	
	
	
}

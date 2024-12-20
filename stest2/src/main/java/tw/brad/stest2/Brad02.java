package tw.brad.stest2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tw.brad.stest2.model.Member;
import tw.brad.stest2.model.Response;
import tw.brad.utils.BCrypt;

@RestController
@RequestMapping("brad02")
public class Brad02 {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private Response response;
	
	@PostMapping(value = {"/member", "/member/{isGetId}"})
	public Response insert(@RequestBody @Valid Member member,
			@PathVariable(required = false) Boolean isGetId) {
		isGetId = isGetId == null?false: isGetId;
		
		String sql = "INSERT INTO member (account,passwd,realname) VALUES (:account, :passwd, :realname)";
		Map<String, String> data = new HashMap<String, String>();
		data.put("account", member.getAccount());
		data.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
		data.put("realname", member.getRealname());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int n = namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(data), keyHolder);		
		int id = keyHolder.getKey().intValue();
		
		response.setError(0);
		response.setMesg("Insert Success");
		response.setInsertId(isGetId?id:-1);
		
		return response;
	}
	
	
}
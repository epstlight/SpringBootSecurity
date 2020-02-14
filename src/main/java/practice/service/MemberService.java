package practice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import practice.domain.Role;
import practice.domain.entity.MemberEntity;
import practice.domain.repository.MemberRepository;
import practice.dto.MemberDto;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
	private MemberRepository memberRepository;

	@Transactional
	public Long joinUser(MemberDto memberDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		return memberRepository.save(memberDto.toEntity()).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
		MemberEntity userEntity = userEntityWrapper.get();

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getVlaue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getVlaue()));
		}

		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}

	public Map<String, String> validateHandling(Errors err){
		Map<String, String> validatorResult = new HashMap<>();
		
		 for (FieldError error : err.getFieldErrors()) {
	            String validKeyName = String.format("valid_%s", error.getField());
	            validatorResult.put(validKeyName, error.getDefaultMessage());
	        }
		 return  validatorResult;
		
	}

}

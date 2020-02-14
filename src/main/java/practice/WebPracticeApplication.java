package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;


@EnableJpaAuditing
@SpringBootApplication
public class WebPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPracticeApplication.class, args);
	}
	
	@Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        return filter;
    }

}

package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * @author Yujie_Zhao
 * @ClassName RibbonConfiguration
 * @Description TODO
 * @Date 2020/9/25  11:20
 * @Version 1.0
 **/
//@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        //command+option+可以快速查看IRule的实现类
        return new RandomRule();
    }
}

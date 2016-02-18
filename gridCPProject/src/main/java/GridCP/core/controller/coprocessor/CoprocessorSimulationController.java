package GridCP.core.controller.coprocessor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import GridCP.core.service.coprocessorService.CoprocessorSimulationService;

@Controller
@RequestMapping(value = "/coprocessorSimulation", produces = "application/json;charset=utf-8")
public class CoprocessorSimulationController {

	@Resource
	private CoprocessorSimulationService coprocessorSimulationService;
}

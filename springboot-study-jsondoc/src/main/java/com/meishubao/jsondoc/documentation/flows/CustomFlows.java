package com.meishubao.jsondoc.documentation.flows;

import com.meishubao.jsondoc.documentation.DocumentationConstants;
import org.jsondoc.core.annotation.flow.ApiFlow;
import org.jsondoc.core.annotation.flow.ApiFlowSet;
import org.jsondoc.core.annotation.flow.ApiFlowStep;

@ApiFlowSet
public class CustomFlows {

	@ApiFlow(
		name = "获取作者详细流程",
		description = "从书单开始获取作者的详细信息",
		steps = {
			@ApiFlowStep(apimethodid = DocumentationConstants.BOOK_FIND_ALL),	
			@ApiFlowStep(apimethodid = DocumentationConstants.BOOK_FIND_ONE),	
			@ApiFlowStep(apimethodid = DocumentationConstants.AUTHOR_FIND_ONE)	
		}
	)
	public void authorDetailFlow() {
	}

}

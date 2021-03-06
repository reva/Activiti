/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.activiti.service.editor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.domain.editor.ModelInformation;
import com.activiti.domain.editor.ModelRelation;
import com.activiti.repository.editor.ModelRelationRepository;

/**
 * @author jbarrez
 */
@Service
@Transactional
public class ModelRelationService {
	
	private static final Logger logger = LoggerFactory.getLogger(ModelRelationService.class);
	
	@Autowired
	private ModelRelationRepository modelRelationRepository;
	
	/**
	 * Returns the models which have a {@link ModelRelation} with the given model, 
	 * where the model references those directly or indirectly.
	 * 
	 * ie modelId here is the parent of the relation.
	 */
	public List<ModelInformation> findReferencedModels(Long modelId) {
		return convert(modelRelationRepository.findModelInformationByParentModelId(modelId));
	}
	
	/**
	 * Returns the models which have a {@link ModelRelation} with the given model,
	 * where the given model is the one that is referenced.
	 * 
	 * ie modelId is the child of the relation
	 */
	public List<ModelInformation> findParentModels(Long modelId) {
		return convert(modelRelationRepository.findModelInformationByChildModelId(modelId));
	}
	
	protected List<ModelInformation> convert(List<Object[]> rawResults) {
		List<ModelInformation> result = new ArrayList<ModelInformation>(rawResults.size());
		for (Object[] rawResult : rawResults) {
			result.add(new ModelInformation(((Number) rawResult[0]).longValue(), (String) rawResult[1], (Integer) rawResult[2]));
		}
		return result;
	}

}

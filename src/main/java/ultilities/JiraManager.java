package ultilities;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;

public class JiraManager {

	public String jiraURL = "https://dtxin.atlassian.net/rest/api/3/issue/";
	public String jiraUserName = "dinhtranxin@gmail.com";
	public String jiraAccessKey = "A2b1FRO9ZNhyAbhoNPL35D6C";
	
	// Create Jira Issue as bug
	public String createIssue(String issueSummary, String projectName, String issueType, 
			String issueDescription, String reporterID, String assigneeID) {
		String issueIDorKey = "";
		
		// The payload definition using the Jackson library
		JsonNodeFactory jnf = JsonNodeFactory.instance;
		ObjectNode payload = jnf.objectNode();
		{
		  ObjectNode fields = payload.putObject("fields");
		  {
		    fields.put("summary", issueSummary);
		    ObjectNode project = fields.putObject("project");
		    {
		      project.put("id", projectName);
		    }
		    ObjectNode issuetype = fields.putObject("issuetype");
		    {
		      issuetype.put("name", issueType);
		    }
		    ObjectNode description = fields.putObject("description");
		    {
		      description.put("type", "doc");
		      description.put("version", 1);
		      ArrayNode content = description.putArray("content");
		      ObjectNode content0 = content.addObject();
		      {
		        content0.put("type", "paragraph");
		        content = content0.putArray("content");
		        content0 = content.addObject();
		        {
		          content0.put("text", issueDescription);
		          content0.put("type", "text");
		        }
		      }
		    }
		    ObjectNode reporter = fields.putObject("reporter");
		    {
		      reporter.put("id", reporterID);
		    }
		    ObjectNode assignee = fields.putObject("assignee");
		    {
		      assignee.put("id", assigneeID);
		    }
		  }
		}

		// Create ObjectMapper
		JacksonObjectMapper mapper = new JacksonObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
	           = new com.fasterxml.jackson.databind.ObjectMapper();

		   public <T> T readValue(String value, Class<T> valueType) {
		       try {
		           return jacksonObjectMapper.readValue(value, valueType);
		       } catch (IOException e) {
		           throw new RuntimeException(e);
		       }
		   }
	
		   public String writeValue(Object value) {
		       try {
		           return jacksonObjectMapper.writeValueAsString(value);
		       } catch (JsonProcessingException e) {
		           throw new RuntimeException(e);
		       }
		   }
		};
		
		// Connect Jackson ObjectMapper to Unirest
		Unirest.config().setObjectMapper(mapper);

		// Uses the 'Unirest' library:
		HttpResponse<JsonNode> response = Unirest.post(jiraURL)
		  .basicAuth(jiraUserName, jiraAccessKey)
		  .header("Accept", "application/json")
		  .header("Content-Type", "application/json")
		  .body(payload)
		  .asJson();
		
		issueIDorKey = response.getBody().getObject().getString("key");	
		
		return issueIDorKey;
	}
	
	public void addAttachmentToJiraIssue(String key, String path) {
		// Uses the 'Unirest' library:
		HttpResponse<JsonNode> response = Unirest.post(jiraURL + key + "/attachments")
         .basicAuth(jiraUserName, jiraAccessKey)
         .header("Accept", "application/json")
         .header("X-Atlassian-Token", "no-check")
         .field("file", new File(path))
         .asJson();

		if(response.getStatus() == 200) {
			System.out.println("Attachment uploaded");
		} else{
			System.out.println("Attachment not uploaded");
		}
	}
}
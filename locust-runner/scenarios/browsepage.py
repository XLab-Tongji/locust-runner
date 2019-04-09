from locust import HttpLocust, TaskSet, task, TaskSequence, seq_task

class UserBehavior(TaskSequence):
	@seq_task(1)
	def browse_index(self):
		self.client.get("/")
		
	@seq_task(2)
	def browse_catalogue(self):
		self.client.get("/category.html")
	
	@seq_task(3)
	@task(4)
	class SubTaskset(TaskSet):
		@task()
		def browse_sock1(self):
			self.client.get("/detail.html?id=3395a43e-2d88-40de-b95f-e00e1502085b")
		
		@task()
		def browse_sock2(self):
			self.client.get("/detail.html?id=03fef6ac-1896-4ce8-bd69-b798f85c6e0b")
		
		@task()
		def browse_sock3(self):
			self.client.get("/detail.html?id=819e1fbf-8b7e-4f6d-811f-693534916a8b")
 
class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
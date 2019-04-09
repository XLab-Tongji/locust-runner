from locust import HttpLocust, TaskSet, task
import random

class UserBehavior(TaskSet):
	@task()
	def register(self):
		rand = random.randrange(1000, 9999, 1)
		self.client.post("/register", json = {"username":str(rand),"password":str(rand),"email":"","firstName":"","lastName":""}, \
		headers = {"Content-Type":"application/json; charset=UTF-8"})
	
class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
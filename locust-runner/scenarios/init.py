from locust import HttpLocust, TaskSet, task
import random

class UserBehavior(TaskSet):
	@task()
	def register1(self):
		self.client.post("/register", json = {"username":"payment","password":"payment","email":"","firstName":"","lastName":""}, \
		headers = {"Content-Type":"application/json; charset=UTF-8"})

	@task()
    def register1(self):
        self.client.post("/register", json = {"username":"addtocart","password":"addtocart","email":"","firstName":"","lastName":""}, \
        headers = {"Content-Type":"application/json; charset=UTF-8"})

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
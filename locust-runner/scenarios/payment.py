from locust import HttpLocust, TaskSet, task
import random

class UserBehavior(TaskSet):
	def on_start(self):
		""" on_start is called when a Locust start before any task is scheduled """
		self.login()

	def on_stop(self):
		""" on_stop is called when the TaskSet is stopping """
		self.logout()

	def login(self):
		self.client.get("/login", auth=('payment','payment'))

	def logout(self):
		self.client.cookies.clear()
	
	@task()
	def payment(self):
		self.client.post("/orders")
	
class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
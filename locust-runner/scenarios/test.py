from locust import HttpLocust, TaskSet, task


class MyTaskSet(TaskSet):
    @task
    def my_task(self):
        r = self.client.get("/")

class MyLocust(HttpLocust):
    task_set = MyTaskSet
    min_wait = 5000
    max_wait = 15000

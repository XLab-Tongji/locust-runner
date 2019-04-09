from locust import HttpLocust, TaskSet, task


class UserBehavior(TaskSet):
    def on_start(self):
        """ on_start is called when a Locust start before any task is scheduled """
        self.login()

    def on_stop(self):
        """ on_stop is called when the TaskSet is stopping """
        self.logout()

    def login(self):
        self.client.get("/login", auth=('addtocart', 'addtocart'))

    def logout(self):
        self.client.cookies.clear()

    @task()
    def addtocart(self):
        self.client.post("/cart", json={"id": "03fef6ac-1896-4ce8-bd69-b798f85c6e0b"},
                         headers={"Content-Type": "application/json; charset=UTF-8"})


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000

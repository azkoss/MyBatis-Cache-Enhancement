from locust import HttpLocust, TaskSet, task

class MceTaskSet(TaskSet):

	@task(10)
	def queryProject(self):
		self.client.get('/project/list')

	@task(2)
	def insertProject(self):
		pass


class MceLocust(HttpLocust):
	host="http://127.0.0.1:8080"
	task_set=MceTaskSet
	max_wait=15000
	min_wait=1500



from django.conf.urls import patterns, url

from bench import views

urlpatterns = patterns('',
    url(r'^$', views.index, name='index'),
    url(r'^deleteall$', views.deleteall, name='deleteall'),
)

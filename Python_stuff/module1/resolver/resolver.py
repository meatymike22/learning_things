import socket


class Resolver:
    def __init__(self):
        self._cache = {}

    def __call__(self, *hosts):
        # iterates through host list and resolves if the website isn't already in the list
        for host in hosts:
            if host not in self._cache:
                self._cache[host] = socket.gethostbyname(host)

        # this sorts the cache by key by converting the dict to a tuple, and using the key to sort the list
        # of tuples, then converts to a dict. name[1] would sort by value
        self._cache = {k: v for k, v in sorted(self._cache.items(), key=lambda name: name[0])}
        return self._cache

    def clear(self):
        self._cache.clear()

    def has_host(self, host):
        return host in self._cache
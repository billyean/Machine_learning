#!/usr/bin/env python3

import tensorflow as tf

say_hello = tf.constant("Hello, my name is Tristan")
sess = tf.Session()

print(sess.run(say_hello))
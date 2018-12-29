from __future__ import print_function
from googleapiclient.discovery import build
from httplib2 import Http
from oauth2client import file, client, tools
from apiclient import errors
import json
import base64

SCOPES = 'https://www.googleapis.com/auth/gmail.readonly'


def GetMessageIdsByLabels(service, user_id, labels):
    try:
        response = service.users().messages().list(userId=user_id,
                                                   labelIds=labels).execute()
        if 'messages' in response:
            return response['messages']
    except errors.HttpError as error:
        print('An error occurred: %s' % error)


def GetMessage(service, user_id, msg_id):
      try:
          message = service.users().messages().get(userId=user_id, id=msg_id).execute()
          print('Message snippet: %s' % message['snippet'])
          return message['payload']['body']
      except errors.HttpError as error:
        print('An error occurred: %s' % error)

def CreateService():
    store = file.Storage('token.json')
    creds = store.get()

    if not creds or creds.invalid:
        flow = client.flow_from_clientsecrets('credentials.json', SCOPES)
        creds = tools.run_flow(flow, store)

    service = build('gmail', 'v1', http=creds.authorize(Http()))

    return service


def main():
    service = CreateService()
    ids = GetMessageIdsByLabels(service, "me", "SPAM")
    count = 0
    for id in ids:
        message = GetMessage(service, 'me', id['id'])
        if message['size'] != 0:
            msg_str = base64.urlsafe_b64decode(message['data'].encode('ASCII'))
            print(msg_str)
            # print(json.dumps(message, indent=4, sort_keys=True))
        if count > 3:
            break
        count = count + 1

    # store = file.Storage('token.json')
    # creds = store.get()
    #
    # if not creds or creds.invalid:
    #     flow = client.flow_from_clientsecrets('credentials.json', SCOPES)
    #     creds = tools.run_flow(flow, store)
    #
    # service = build('gmail', 'v1', http=creds.authorize(Http()))
    #
    # try:
    #     response = service.users().messages().list(userId='me',
    #                                                labelIds=['SPAM']).execute()
    #     if 'messages' in response:
    #         messagesIds = response['messages']
    #
    # except errors.HttpError as error:
    #     print('An error occurred: %s' % error)
    #
    # # results = service.users().labels().list(userId='me').execute()
    # # labels = results.get('labels', [])
    # #
    # # if not labels:
    # #     print('No labels found.')
    # # else:
    # #     print('Labels:')
    # #     for label in labels:
    # #         print(label['name'])


if __name__ == '__main__':
    main()